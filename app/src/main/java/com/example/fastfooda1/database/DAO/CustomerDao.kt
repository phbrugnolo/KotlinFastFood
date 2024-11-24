package com.example.fastfooda1.database.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.fastfooda1.models.Address
import com.example.fastfooda1.models.Customer
import com.example.fastfooda1.models.CustomerWithAddress
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomerDao {

    @Query("SELECT * FROM customers ORDER BY id ASC")
    fun getAllCustomers(): Flow<List<Customer>>


    @Query("SELECT * FROM customers WHERE id = :id")
    fun getCustomer(id: Int): Flow<Customer>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(customer: Customer)

    @Update
    suspend fun update(customer: Customer)

    @Delete
    suspend fun delete(customer: Customer)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAddress(address: Address)

    @Update
    suspend fun updateAddress(address: Address)

    @Transaction
    @Query("SELECT * FROM customers WHERE id = :customerId")
    fun getCustomerWithAddress(customerId: Int): Flow<CustomerWithAddress>
}
