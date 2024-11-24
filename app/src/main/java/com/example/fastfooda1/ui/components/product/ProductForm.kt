package com.example.fastfooda1.ui.components.product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import java.math.BigDecimal


@Composable
fun ProductForm(
    product: Product? = null,
    onSave: (Product) -> Unit,
    onCancel: () -> Unit
) {
    var name by remember { mutableStateOf(product?.name ?: "") }
    var price by remember { mutableStateOf(product?.price?.toString() ?: "") }
    var quantity by remember { mutableStateOf(product?.quantity?.toString() ?: "") }

    Column(Modifier.padding(16.dp)) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nome do Produto") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = price,
            onValueChange = { price = it },
            label = { Text("Preço") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        TextField(
            value = quantity,
            onValueChange = { quantity = it },
            label = { Text("Quantidade") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Button(onClick = onCancel) {
                Text("Cancelar")
            }
            Button(
                onClick = {
                    val newProduct = Product(
                        id = product?.id ?: 0,
                        name = name,
                        price = BigDecimal(price),
                        image = product?.image ?: R.drawable.ic_launcher_background,
                        quantity = quantity.toIntOrNull() ?: 0
                    )
                    onSave(newProduct)
                }
            ) {
                Text("Salvar")
            }
        }
    }
}
