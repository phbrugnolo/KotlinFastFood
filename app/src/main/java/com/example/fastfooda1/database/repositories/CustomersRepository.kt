package com.example.fastfooda1.database.repositories

import com.example.fastfooda1.models.Customer
import kotlinx.coroutines.flow.Flow

interface CustomersRepository {
    fun getAllCustomersStream(): Flow<List<Customer>>

    fun getCustomerStream(id: Int): Flow<Customer?>

    suspend fun insertCustomer(customer: Customer)

    suspend fun updateCustomer(customer: Customer)

    suspend fun deleteCustomer(customer: Customer)
}
