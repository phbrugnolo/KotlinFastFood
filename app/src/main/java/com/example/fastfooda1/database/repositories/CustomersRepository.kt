package com.example.fastfooda1.database.repositories

import com.example.fastfooda1.models.Address
import com.example.fastfooda1.models.Customer
import com.example.fastfooda1.models.CustomerWithAddress
import kotlinx.coroutines.flow.Flow

interface CustomersRepository {
    fun getAllCustomersStream(): Flow<List<Customer>>

    fun getCustomerStream(id: Int): Flow<Customer?>

    fun getCustomerWithAddressStream(id: Int): Flow<CustomerWithAddress?>

    suspend fun insertCustomer(customer: Customer)

    suspend fun updateCustomer(customer: Customer)

    suspend fun deleteCustomer(customer: Customer)

    suspend fun insertAddress(address: Address)

    suspend fun updateAddress(address: Address)
}
