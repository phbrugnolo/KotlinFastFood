package com.example.fastfooda1.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fastfooda1.database.repositories.CustomersRepository
import com.example.fastfooda1.database.repositories.ProductsRepository
import com.example.fastfooda1.database.repositories.SalesRepository

class SalesViewModelFactory(
    private val salesRepository: SalesRepository,
    private val customersRepository: CustomersRepository,
    private val productsRepository: ProductsRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SalesViewModel::class.java)) {
            return SalesViewModel(salesRepository, customersRepository, productsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
