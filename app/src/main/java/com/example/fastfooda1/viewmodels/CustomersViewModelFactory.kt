package com.example.fastfooda1.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fastfooda1.database.repositories.CustomersRepository

class CustomersViewModelFactory(private val repository: CustomersRepository) : ViewModelProvider.Factory  {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CustomersViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CustomersViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}