package com.example.fastfooda1.database.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.fastfooda1.models.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query("SELECT * FROM products ORDER BY id ASC")
    fun getAllProducts(): Flow<List<Product>>


    @Query("SELECT * FROM products WHERE id = :id")
    fun getProduct(id: Int): Flow<Product>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(product: Product)

    @Update
    suspend fun update(product: Product)

    @Delete
    suspend fun delete(product: Product)
}
