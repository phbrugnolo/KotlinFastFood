package com.example.fastfooda1.ui.screens.customer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fastfooda1.models.Address
import com.example.fastfooda1.models.Customer
import com.example.fastfooda1.viewmodels.CustomerFormViewModel

@Composable
fun CustomerFormScreen(
    viewModel: CustomerFormViewModel,
    onSave: (Customer, Address) -> Unit,
    onCancel: () -> Unit
) {
    val customer = viewModel.customer
    val address = viewModel.address
    val loading = viewModel.loading
    val error = viewModel.error

    Column(
        Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)

    ) {
        OutlinedTextField(
            value = customer.name,
            onValueChange = { viewModel.updateCustomer(customer.copy(name = it)) },
            label = { Text("Nome") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = customer.cpf,
            onValueChange = { viewModel.updateCustomer(customer.copy(cpf = it)) },
            label = { Text("CPF") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = address.cep,
            onValueChange = {
                viewModel.updateAddress(address.copy(cep = it))
                if (it.length == 8) viewModel.fetchAddressByCep(it)
            },
            label = { Text("CEP") },
            modifier = Modifier.fillMaxWidth(),
            isError = error.isNotEmpty()
        )
        if (loading) {
            CircularProgressIndicator(Modifier.align(Alignment.CenterHorizontally))
        }
        if (error.isNotEmpty()) {
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        OutlinedTextField(
            value = address.logradouro,
            onValueChange = { viewModel.updateAddress(address.copy(logradouro = it)) },
            label = { Text("Logradouro") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = address.complemento ?: "",
            onValueChange = { viewModel.updateAddress(address.copy(complemento = it)) },
            label = { Text("Complemento") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = address.bairro,
            onValueChange = { viewModel.updateAddress(address.copy(bairro = it)) },
            label = { Text("Bairro") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = address.localidade,
            onValueChange = { viewModel.updateAddress(address.copy(localidade = it)) },
            label = { Text("Cidade") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = address.uf,
            onValueChange = { viewModel.updateAddress(address.copy(uf = it)) },
            label = { Text("UF") },
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = onCancel) {
                Text("Cancelar")
            }
            Button(onClick = { onSave(customer, address) }) {
                Text("Salvar")
            }
        }
    }
}