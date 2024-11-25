package com.example.fastfooda1.database.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.fastfooda1.models.Sale
import com.example.fastfooda1.models.SaleWithDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface SaleDao {
    @Insert
    suspend fun insertSale(sale: Sale)

    @Transaction
    @Query("SELECT * FROM sales WHERE id = :saleId")
    fun getSaleWithDetails(saleId: Int): Flow<SaleWithDetails>

    @Transaction
    @Query("SELECT * FROM sales")
    fun getAllSalesWithDetails(): Flow<List<SaleWithDetails>>
}
