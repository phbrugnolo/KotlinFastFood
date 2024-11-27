package com.example.fastfooda1.ui.screens.customer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fastfooda1.models.Customer
import com.example.fastfooda1.viewmodels.CustomersViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomerListScreen(
    viewModel: CustomersViewModel,
    onNavigateToInsertCustomer: () -> Unit,
    onNavigateToEditCustomer: (Int) -> Unit
) {
    val customers by viewModel.customers.collectAsState(emptyList())
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Clientes") },
                colors = TopAppBarDefaults.mediumTopAppBarColors()
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToInsertCustomer) {
                Icon(Icons.Default.Add, contentDescription = "Cadastrar Cliente")
            }
        }
    ) { padding ->
        LazyColumn(
            contentPadding = padding,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
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
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable { onEdit() },
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        elevation = CardDefaults.elevatedCardElevation()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = customer.name,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "CPF: ${customer.cpf}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "CEP: ${customer.cep}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Endereço: ${customer.logradouro}, ${customer.complemento} Bairro: ${customer.bairro}, Cidade: ${customer.localidade}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
