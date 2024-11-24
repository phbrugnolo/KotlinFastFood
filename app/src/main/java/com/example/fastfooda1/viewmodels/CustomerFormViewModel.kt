package com.example.fastfooda1.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.fastfooda1.controllers.CepController
import com.example.fastfooda1.models.Address
import com.example.fastfooda1.models.Customer

class CustomerFormViewModel(private val cepController: CepController) : ViewModel() {

    var customer by mutableStateOf(Customer(name = "", cpf = ""))
        private set

    var address by mutableStateOf(Address(cep = "", logradouro = "", complemento = null, bairro = "", localidade = "", uf = "", estado = "", erro = false, customerId = 1))
        private set

    var loading by mutableStateOf(false)
    var error by mutableStateOf("")

    fun updateCustomer(updatedCustomer: Customer) {
        customer = updatedCustomer
    }

    fun updateAddress(updatedAddress: Address) {
        address = updatedAddress
    }

    fun fetchAddressByCep(cep: String) {
        loading = true
        error = ""
        cepController.getAddress(
            cep = cep,
            onSuccess = {
                address = it
                loading = false
            },
            onError = {
                error = "CEP inválido ou não encontrado"
                loading = false
            }
        )
    }
}