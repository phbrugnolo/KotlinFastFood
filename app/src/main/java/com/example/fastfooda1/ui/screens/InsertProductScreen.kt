package com.example.fastfooda1.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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

    Column(Modifier.padding(16.dp)) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nome do Produto") }
        )
        TextField(
            value = price,
            onValueChange = { price = it },
            label = { Text("Preço") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        TextField(
            value = quantity,
            onValueChange = { quantity = it },
            label = { Text("Quantidade") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Button(onClick = {
            viewModel.insertProduct(
                Product(
                    name = name,
                    price = price.toDoubleOrNull() ?: 0.0,
                    image = R.drawable.ic_launcher_background,
                    quantity = quantity.toIntOrNull() ?: 0
                )
            )
            onBack()
        }) {
            Text("Salvar")
        }
    }
}