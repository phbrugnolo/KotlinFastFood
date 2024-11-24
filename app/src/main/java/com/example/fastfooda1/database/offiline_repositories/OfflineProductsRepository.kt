package com.example.fastfooda1.database.offiline_repositories

import com.example.fastfooda1.database.DAO.ProductDao
import com.example.fastfooda1.database.repositories.ProductsRepository
import com.example.fastfooda1.models.Product
import kotlinx.coroutines.flow.Flow

class OfflineProductsRepository(private val productDao: ProductDao) : ProductsRepository {
    override fun getAllProductsStream(): Flow<List<Product>> = productDao.getAllProducts()

    override fun getProductStream(id: Int): Flow<Product?> = productDao.getProduct(id)

    override suspend fun insertProduct(product: Product) = productDao.insert(product)

    override suspend fun deleteProduct(product: Product) = productDao.delete(product)

    override suspend fun updateProduct(product: Product) = productDao.update(product)
}