package com.example.fastfooda1.database.repositories

import com.example.fastfooda1.models.Product
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {

    fun getAllProductsStream(): Flow<List<Product>>

    fun getProductStream(id: Int): Flow<Product?>

    suspend fun insertProduct(product: Product)

    suspend fun updateProduct(product: Product)

    suspend fun deleteProduct(product: Product)
}