package com.example.fastfooda1.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.fastfooda1.ui.components.product.ProductItem
import com.example.fastfooda1.viewmodels.ProductsViewModel

@Composable
fun ProductListScreen(
    viewModel: ProductsViewModel,
    onNavigateToInsertProduct: () -> Unit,
    onNavigateToEditProduct: (Int) -> Unit
) {
    val products by viewModel.products.collectAsState()

    Column {
        LazyColumn {
            items(products) {product ->
                ProductItem(
                    product = product,
                    onEdit = { onNavigateToEditProduct(product.id) },
                    onDelete = { viewModel.deleteProduct(product) }
                )
            }
        }
        Button(onClick = onNavigateToInsertProduct) {
            Text("Adicionar Produto")
        }
    }
}