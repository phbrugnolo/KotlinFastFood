package com.example.fastfooda1.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.fastfooda1.models.Product
import kotlinx.coroutines.launch

class ProductViewModel(application: Application) : AndroidViewModel(application) {
    private val productDao = FastFoodDatabase.getDatabase(application).productDao()

    val products = productDao.getAllProducts()

    fun insertProduct(product: Product) {
        viewModelScope.launch {
            productDao.insert(product)
        }
    }

    fun updateProduct(product: Product) {
        viewModelScope.launch {
            productDao.update(product)
        }
    }

    fun deleteProduct(product: Product) {
        viewModelScope.launch {
            productDao.delete(product)
        }
    }

    suspend fun getAllProducts() = productDao.getAllProducts()
}