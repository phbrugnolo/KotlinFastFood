package com.example.fastfooda1.ui.screens.customer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fastfooda1.models.Customer
import com.example.fastfooda1.viewmodels.CustomersViewModel

@Composable
fun CustomerListScreen(
    viewModel: CustomersViewModel,
    onNavigateToInsertCustomer: () -> Unit,
    onNavigateToEditCustomer: (Int) -> Unit
) {
    val customers by viewModel.customers.collectAsState(emptyList())
    Column {
        Button(
            onClick = onNavigateToInsertCustomer,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cadastar cliente")
        }
        LazyColumn {
            items(customers) { customer ->
                CustomerItem(
                    customer = customer,
                    onEdit = { onNavigateToEditCustomer(customer.id) }
                )
            }
        }
    }
}

@Composable
fun CustomerItem(customer: Customer, onEdit: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onEdit() }
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = customer.name, style = MaterialTheme.typography.titleMedium)
            Text(text = customer.cpf, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
