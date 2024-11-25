package com.example.fastfooda1.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fastfooda1.database.repositories.CustomersRepository
import com.example.fastfooda1.getAddressFromCep
import com.example.fastfooda1.models.Customer
import com.example.fastfooda1.models.ViaCepResponse
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CustomersViewModel(private val repository: CustomersRepository) : ViewModel() {
    val customers: StateFlow<List<Customer>> = repository.getAllCustomersStream().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun getCustomer(id: Int) = repository.getCustomerStream(id)

    fun insertCustomer(customer: Customer) {
        viewModelScope.launch { repository.insertCustomer(customer) }
    }

    fun updateCustomer(customer: Customer) {
        viewModelScope.launch { repository.updateCustomer(customer) }
    }

    fun deleteCustomer(customer: Customer) {
        viewModelScope.launch { repository.deleteCustomer(customer) }
    }

    var address = mutableStateOf(ViaCepResponse(null, null, null, null, null, null, null))
    var isLoading = mutableStateOf(false)

    fun fetchAddress(cep: String) {
        viewModelScope.launch {
            isLoading.value = true
            try {
                val result = getAddressFromCep(cep)
                Log.d("CustomersViewModel", "Endereço retornado: $result")
                address.value = result ?: ViaCepResponse(null, null, null, null, null, null, null)
            } catch (e: Exception) {
                Log.e("CustomersViewModel", "Erro ao buscar o CEP: ${e.message}")
            } finally {
                isLoading.value = false
            }
        }
    }
}
