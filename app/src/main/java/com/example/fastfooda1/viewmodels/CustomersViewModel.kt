package com.example.fastfooda1.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fastfooda1.database.repositories.CustomersRepository
import com.example.fastfooda1.models.Customer
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CustomersViewModel(private val repository: CustomersRepository) : ViewModel() {
    val customers = repository.getAllCustomersStream().stateIn(
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
}
