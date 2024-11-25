package com.example.fastfooda1.database.offiline_repositories

import com.example.fastfooda1.database.DAO.SaleDao
import com.example.fastfooda1.database.repositories.SalesRepository
import com.example.fastfooda1.models.Sale
import com.example.fastfooda1.models.SaleWithDetails
import kotlinx.coroutines.flow.Flow

class OfflineSalesRepository(private val saleDao: SaleDao) : SalesRepository {

    override suspend fun insertSale(sale: Sale) = saleDao.insertSale(sale)


    override fun getAllSalesWithDetailsStream(): Flow<List<SaleWithDetails>> =
        saleDao.getAllSalesWithDetails()


    override fun getSaleWithDetailsStream(saleId: Int): Flow<SaleWithDetails?> =
        saleDao.getSaleWithDetails(saleId)

}