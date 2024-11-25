package com.example.fastfooda1.ui.screens.customer

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fastfooda1.models.Customer
import com.example.fastfooda1.viewmodels.CustomersViewModel

@Composable
fun InsertCustomerScreen(viewModel: CustomersViewModel, onBack: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var cpf by remember { mutableStateOf("") }
    var cep by remember { mutableStateOf("") }

    val address = viewModel.address.value
    val isLoading = viewModel.isLoading.value

    LaunchedEffect(cep) {
        // Log do CEP atual
        Log.d("InsertCustomerScreen", "CEP digitado: $cep")
        if (cep.length == 8) {
            Log.d("InsertCustomerScreen", "CEP válido detectado, chamando fetchAddress")
            viewModel.fetchAddress(cep)
        } else {
            Log.d("InsertCustomerScreen", "CEP ainda não é válido")
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nome") },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading
        )
        TextField(
            value = cpf,
            onValueChange = { cpf = it },
            label = { Text("CPF") },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading
        )
        TextField(
            value = cep,
            onValueChange = { cep = it },
            label = { Text("CEP") },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading
        )

        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }

        TextField(
            value = address.logradouro.orEmpty(),
            onValueChange = {},
            label = { Text("Logradouro") },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading
        )
        TextField(
            value = address.complemento.orEmpty(),
            onValueChange = {},
            label = { Text("Complemento") },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading
        )
        TextField(
            value = address.bairro.orEmpty(),
            onValueChange = {},
            label = { Text("Bairro") },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading
        )
        TextField(
            value = address.localidade.orEmpty(),
            onValueChange = {},
            label = { Text("Localidade") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = address.uf.orEmpty(),
            onValueChange = {},
            label = { Text("UF") },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading
        )

        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Button(onClick = onBack, modifier = Modifier.weight(1f)) {
                Text("Cancelar")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    viewModel.insertCustomer(
                        Customer(
                            name = name,
                            cpf = cpf,
                            cep = cep,
                            logradouro = address.logradouro,
                            complemento = address.complemento,
                            localidade = address.localidade,
                            estado = address.estado,
                            uf = address.uf,
                            bairro = address.bairro
                        )
                    )
                    onBack()
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Salvar")
            }
        }
    }
}
