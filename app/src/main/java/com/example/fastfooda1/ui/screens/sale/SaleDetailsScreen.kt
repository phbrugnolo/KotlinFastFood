package com.example.fastfooda1.ui.screens.sale

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fastfooda1.viewmodels.SalesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SaleDetailsScreen(
    viewModel: SalesViewModel,
    saleId: Int,
    onBack: () -> Unit
) {
    val saleDetails by viewModel.getSaleDetails(saleId).collectAsState(initial = null)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Sale Details") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        if (saleDetails == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Customer: ${saleDetails!!.customer.name}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "Product: ${saleDetails!!.product.name}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "Quantity: ${saleDetails!!.sale.quantity}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "Total Price: $${saleDetails!!.sale.totalPrice}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Date: ${saleDetails!!.sale.date}",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

