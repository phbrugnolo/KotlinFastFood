package com.example.fastfooda1.database.offiline_repositories

import com.example.fastfooda1.database.DAO.CustomerDao
import com.example.fastfooda1.database.repositories.CustomersRepository
import com.example.fastfooda1.models.Address
import com.example.fastfooda1.models.Customer
import com.example.fastfooda1.models.CustomerWithAddress
import kotlinx.coroutines.flow.Flow

class OfflineCustomersRepository(private val customerDao: CustomerDao) : CustomersRepository {
    override fun getAllCustomersStream(): Flow<List<Customer>> = customerDao.getAllCustomers()

    override fun getCustomerStream(id: Int): Flow<Customer?> = customerDao.getCustomer(id)

    override fun getCustomerWithAddressStream(id: Int): Flow<CustomerWithAddress?> =
        customerDao.getCustomerWithAddress(id)

    override suspend fun insertCustomer(customer: Customer) {
        customerDao.insert(customer)
    }

    override suspend fun updateCustomer(customer: Customer) {
        customerDao.update(customer)
    }

    override suspend fun deleteCustomer(customer: Customer) {
        customerDao.delete(customer)
    }

    override suspend fun insertAddress(address: Address) {
        customerDao.insertAddress(address)
    }

    override suspend fun updateAddress(address: Address) {
        customerDao.updateAddress(address)
    }
}
