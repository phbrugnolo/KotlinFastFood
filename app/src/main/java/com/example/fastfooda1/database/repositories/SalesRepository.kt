package com.example.fastfooda1.database.repositories

import com.example.fastfooda1.models.Sale
import com.example.fastfooda1.models.SaleWithDetails
import kotlinx.coroutines.flow.Flow

interface SalesRepository {
    fun getAllSalesWithDetailsStream(): Flow<List<SaleWithDetails>>

    fun getSaleWithDetailsStream(saleId: Int): Flow<SaleWithDetails?>

    suspend fun insertSale(sale: Sale)
}