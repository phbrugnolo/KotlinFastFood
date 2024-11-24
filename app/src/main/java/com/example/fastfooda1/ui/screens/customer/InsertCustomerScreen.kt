package com.example.fastfooda1.ui.screens.customer

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import com.example.fastfooda1.models.Customer
import com.example.fastfooda1.viewmodels.CustomersViewModel

@Composable
fun InsertCustomerScreen(viewModel: CustomersViewModel, onBack: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var cpf by remember { mutableStateOf("") }
    // Continue para os outros campos...

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = cpf,
            onValueChange = { cpf = it },
            label = { Text("CPF") },
            modifier = Modifier.fillMaxWidth()
        )
        // Adicione outros TextFields aqui para os demais campos

        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Button(onClick = onBack, modifier = Modifier.weight(1f)) {
                Text("Cancel")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    viewModel.insertCustomer(
                        Customer(
                            name = name,
                            cpf = cpf,
                            // Preencha os outros campos aqui...
                        )
                    )
                    onBack()
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Save")
            }
        }
    }
}
