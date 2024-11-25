package com.example.fastfooda1.ui.screens.sale

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.fastfooda1.models.Customer
import com.example.fastfooda1.models.Product
import com.example.fastfooda1.models.Sale
import com.example.fastfooda1.viewmodels.SalesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertSaleScreen(
    viewModel: SalesViewModel,
    onBack: () -> Unit
) {
    val customers by viewModel.customers.collectAsState()
    val products by viewModel.products.collectAsState()

    var selectedCustomer by remember { mutableStateOf<Customer?>(null) }
    var selectedProduct by remember { mutableStateOf<Product?>(null) }
    var quantity by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val isValid = selectedCustomer != null &&
            selectedProduct != null &&
            quantity.toIntOrNull() != null &&
            (quantity.toIntOrNull()?.let { it > 0 && it <= (selectedProduct?.quantity ?: 0) } ?: false)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Realizar Compra") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CustomDropdownMenu(
                label = "Selecione um Cliente",
                options = customers,
                selectedOption = selectedCustomer,
                onOptionSelected = { selectedCustomer = it },
                displayText = { it.name }
            )

            CustomDropdownMenu(
                label = "Selecione um Produto",
                options = products,
                selectedOption = selectedProduct,
                onOptionSelected = { selectedProduct = it },
                displayText = { it.name }
            )

            OutlinedTextField(
                value = quantity,
                onValueChange = {
                    quantity = it
                    errorMessage = if (it.toIntOrNull() != null && selectedProduct != null && it.toInt() > (selectedProduct!!.quantity)) {
                        "Quantidade excede o estoque disponível!"
                    } else null
                },
                label = { Text("Quantidade") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                isError = errorMessage != null
            )

            errorMessage?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Button(
                onClick = {
                    if (isValid) {
                        val sale = Sale(
                            customerId = selectedCustomer!!.id,
                            productId = selectedProduct!!.id,
                            quantity = quantity.toInt(),
                            totalPrice = quantity.toInt() * selectedProduct!!.price,
                            date = System.currentTimeMillis()
                        )
                        try {
                            viewModel.insertSaleWithDecrement(sale)
                            onBack()
                        } catch (e: IllegalStateException) {
                            errorMessage = e.message
                        }
                    }
                },
                enabled = isValid,
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Salvar")
            }
        }
    }
}

@Composable
fun <T> CustomDropdownMenu(
    label: String,
    options: List<T>,
    selectedOption: T?,
    onOptionSelected: (T) -> Unit,
    displayText: (T) -> String
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        OutlinedTextField(
            value = selectedOption?.toString() ?: "",
            onValueChange = {},
            label = { Text(label) },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true },
            enabled = false,
            readOnly = true
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(displayText(option)) },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}


