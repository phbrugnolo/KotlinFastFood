package com.example.fastfooda1.ui.screens.product

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.fastfooda1.R
import com.example.fastfooda1.models.Product
import com.example.fastfooda1.viewmodels.ProductsViewModel

@Composable
fun InsertProductScreen(viewModel: ProductsViewModel, onBack: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var nameError by remember { mutableStateOf(false) }
    var priceError by remember { mutableStateOf(false) }
    var quantityError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Text(
            text = "Adicionar Novo Produto",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Nome do Produto
        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
                nameError = it.isEmpty()
            },
            label = { Text("Nome do Produto") },
            isError = nameError,
            modifier = Modifier.fillMaxWidth()
        )
        if (nameError) {
            Text(
                text = "O nome do produto não pode ser vazio",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Preço
        OutlinedTextField(
            value = price,
            onValueChange = {
                price = it
                priceError = it.toDoubleOrNull() == null && it.isNotEmpty()
            },
            label = { Text("Preço") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = priceError,
            modifier = Modifier.fillMaxWidth()
        )
        if (priceError) {
            Text(
                text = "Digite um preço válido",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = quantity,
            onValueChange = {
                quantity = it
                quantityError = it.toIntOrNull() == null && it.isNotEmpty()
            },
            label = { Text("Quantidade") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = quantityError,
            modifier = Modifier.fillMaxWidth()
        )
        if (quantityError) {
            Text(
                text = "Digite uma quantidade válida",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (name.isNotEmpty() && price.toDoubleOrNull() != null && quantity.toIntOrNull() != null) {
                    viewModel.insertProduct(
                        Product(
                            name = name,
                            price = price.toDouble(),
                            image = R.drawable.ic_launcher_background,
                            quantity = quantity.toInt()
                        )
                    )
                    onBack()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium
        ) {
            Text("Salvar Produto")
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = onBack) {
            Text("Voltar", style = MaterialTheme.typography.bodyMedium)
        }
    }
}