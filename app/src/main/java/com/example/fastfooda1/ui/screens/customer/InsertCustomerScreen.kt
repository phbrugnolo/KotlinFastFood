package com.example.fastfooda1.ui.screens.customer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.fastfooda1.models.Customer
import com.example.fastfooda1.util.MaskedTextField
import com.example.fastfooda1.viewmodels.CustomersViewModel

@Composable
fun InsertCustomerScreen(
    viewModel: CustomersViewModel,
    onBack: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var cpf by remember { mutableStateOf("") }
    var cep by remember { mutableStateOf("") }
    var complemento by remember { mutableStateOf("") }
    var showCpfError by remember { mutableStateOf(false) }
    var showCepError by remember { mutableStateOf(false) }

    val address = viewModel.address.value
    val isLoading = viewModel.isLoading.value

    LaunchedEffect(cep) {
        if (cep.length == 8 && cep.all { it.isDigit() }) {
            viewModel.fetchAddress(cep)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = "Cadastrar Cliente",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))
            // Nome
            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nome") },
                modifier = Modifier.fillMaxWidth(),
                enabled = !isLoading,
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    capitalization = KeyboardCapitalization.Words,
                    imeAction = ImeAction.Next
                )
            )

            Spacer(modifier = Modifier.height(8.dp))
            // CPF
            MaskedTextField(
                value = cpf,
                onValueChange = {
                    cpf = it
                    showCpfError = it.length != 11
                },
                label = "CPF",
                mask = "###.###.###-##",
                modifier = Modifier.fillMaxWidth(),
                isError = showCpfError,
                enabled = !isLoading,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )
            if (showCpfError) {
                Text(
                    text = "CPF inválido",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            // CEP
            MaskedTextField(
                value = cep,
                onValueChange = {
                    cep = it
                    showCepError = it.length != 8
                },
                label = "CEP",
                mask = "#####-###",
                modifier = Modifier.fillMaxWidth(),
                isError = showCepError,
                enabled = !isLoading,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )
            if (showCepError) {
                Text(
                    text = "CEP inválido",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            // Logradouro
            TextField(
                value = address.logradouro.orEmpty(),
                onValueChange = {},
                label = { Text("Logradouro") },
                modifier = Modifier.fillMaxWidth(),
                enabled = false
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = complemento,
                onValueChange = { complemento = it },
                label = { Text("Número") },
                modifier = Modifier.fillMaxWidth(),
                enabled = !isLoading,
                singleLine = !isLoading
            )

            Spacer(modifier = Modifier.height(8.dp))
            // Bairro
            TextField(
                value = address.bairro.orEmpty(),
                onValueChange = {},
                label = { Text("Bairro") },
                modifier = Modifier.fillMaxWidth(),
                enabled = false
            )

            Spacer(modifier = Modifier.height(8.dp))
            // Localidade
            TextField(
                value = address.localidade.orEmpty(),
                onValueChange = {},
                label = { Text("Localidade") },
                modifier = Modifier.fillMaxWidth(),
                enabled = false
            )

            Spacer(modifier = Modifier.height(8.dp))
            // UF
            TextField(
                value = address.uf.orEmpty(),
                onValueChange = {},
                label = { Text("UF") },
                modifier = Modifier.fillMaxWidth(),
                enabled = false
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        // Botões
        Row {
            Button(
                onClick = onBack,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
            ) {
                Text("Cancelar")
            }
            Button(
                onClick = {
                    if (cpf.length == 11 && cep.length == 8 && name.isNotBlank() && complemento.isNotBlank()) {
                        viewModel.insertCustomer(
                            Customer(
                                name = name,
                                cpf = cpf,
                                cep = cep,
                                logradouro = address.logradouro,
                                complemento = complemento,
                                localidade = address.localidade,
                                estado = address.estado,
                                uf = address.uf,
                                bairro = address.bairro
                            )
                        )
                        onBack()
                    }
                },
                modifier = Modifier.weight(1f),
                enabled = !isLoading
            ) {
                Text("Salvar")
            }
        }
    }
}


