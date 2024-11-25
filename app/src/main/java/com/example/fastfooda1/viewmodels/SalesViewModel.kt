package com.example.fastfooda1.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fastfooda1.database.repositories.CustomersRepository
import com.example.fastfooda1.database.repositories.ProductsRepository
import com.example.fastfooda1.database.repositories.SalesRepository
import com.example.fastfooda1.models.Customer
import com.example.fastfooda1.models.Product
import com.example.fastfooda1.models.Sale
import com.example.fastfooda1.models.SaleWithDetails
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SalesViewModel(
    private val salesRepository: SalesRepository,
    private val customersRepository: CustomersRepository,
    private val productsRepository: ProductsRepository
) : ViewModel() {

    val sales: StateFlow<List<SaleWithDetails>> =
        salesRepository.getAllSalesWithDetailsStream().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun insertSale(sale: Sale) = viewModelScope.launch {
        salesRepository.insertSale(sale)
    }

    val customers: StateFlow<List<Customer>> = customersRepository.getAllCustomersStream()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val products: StateFlow<List<Product>> = productsRepository.getAllProductsStream()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun getSaleDetails(saleId: Int) = salesRepository.getSaleWithDetailsStream(saleId)

    fun insertSaleWithDecrement(sale: Sale) = viewModelScope.launch {
        val product = productsRepository.getProductStream(sale.productId)
            .firstOrNull()

        if (product != null) {
            if (product.quantity >= sale.quantity) {
                productsRepository.updateProduct(
                    product.copy(quantity = product.quantity - sale.quantity)
                )
                salesRepository.insertSale(sale)
            } else {
                throw IllegalStateException("Estoque insuficiente para realizar a venda")
            }
        } else {
            throw IllegalStateException("Produto não encontrado")
        }
    }
}
