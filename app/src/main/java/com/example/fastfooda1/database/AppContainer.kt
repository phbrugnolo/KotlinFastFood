package com.example.fastfooda1.database

import android.content.Context
import com.example.fastfooda1.database.offiline_repositories.OfflineCustomersRepository
import com.example.fastfooda1.database.offiline_repositories.OfflineProductsRepository
import com.example.fastfooda1.database.repositories.CustomersRepository
import com.example.fastfooda1.database.repositories.ProductsRepository

interface AppContainer {
    val productsRepository: ProductsRepository
    val customersRepository: CustomersRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val productsRepository: ProductsRepository by lazy {
        OfflineProductsRepository(FastFoodDatabase.getDatabase(context).productDao())
    }

    override val customersRepository: CustomersRepository by lazy {
        OfflineCustomersRepository(FastFoodDatabase.getDatabase(context).customerDao())
    }
}