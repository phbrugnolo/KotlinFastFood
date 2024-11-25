package com.example.fastfooda1.ui.screens.product

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.fastfooda1.viewmodels.ProductsViewModel


@Composable
fun EditProductScreen(
    viewModel: ProductsViewModel,
    productId: Int,
    onBack: () -> Unit
) {
    val product by viewModel.getProduct(productId).collectAsState(initial = null)

    if (product != null) {
        var name by remember { mutableStateOf(product!!.name) }
        var price by remember { mutableStateOf(product!!.price.toString()) }
        var quantity by remember { mutableStateOf(product!!.quantity.toString()) }

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Text(
                text = "Editar Produto",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Nome do Produto
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nome do Produto") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Preço
            OutlinedTextField(
                value = price,
                onValueChange = { price = it },
                label = { Text("Preço") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Quantidade
            OutlinedTextField(
                value = quantity,
                onValueChange = { quantity = it },
                label = { Text("Quantidade") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    viewModel.updateProduct(
                        product!!.copy(
                            name = name,
                            price = price.toDoubleOrNull() ?: 0.0,
                            quantity = quantity.toIntOrNull() ?: 0
                        )
                    )
                    onBack()
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Salvar Alterações")
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = onBack) {
                Text("Voltar", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
