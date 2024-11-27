package com.example.fastfooda1.ui.screens.customer

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.fastfooda1.models.Customer
import com.example.fastfooda1.util.MaskedTextField
import com.example.fastfooda1.viewmodels.CustomersViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditCustomerScreen(
    viewModel: CustomersViewModel, customerId: Int, onBack: () -> Unit
) {
    val customer by viewModel.getCustomer(customerId).collectAsState(initial = null)

    val isLoading = viewModel.isLoading.value
    val address = viewModel.address.value

    if (customer != null) {
        var name by remember { mutableStateOf(customer!!.name) }
        var cpf by remember { mutableStateOf(customer!!.cpf) }
        var cep by remember { mutableStateOf(customer!!.cep) }
        var complemento by remember { mutableStateOf(customer!!.complemento) }

        var showCpfError by remember { mutableStateOf(false) }
        var showCepError by remember { mutableStateOf(false) }

        LaunchedEffect(cep) {
            if (cep.length == 8 && cep.all { it.isDigit() }) {
                viewModel.fetchAddress(cep)
            }
        }

        Scaffold(topBar = {
            TopAppBar(title = { Text("Editar Cliente") }, navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
                }
            }, colors = TopAppBarDefaults.mediumTopAppBarColors()
            )
        }) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Nome
                TextField(value = name,
                    onValueChange = { name = it },
                    label = { Text("Nome") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

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
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number, imeAction = ImeAction.Next
                    )
                )
                if (showCpfError) {
                    Text(
                        text = "CPF inválido",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

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
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number, imeAction = ImeAction.Next
                    )
                )
                if (showCepError) {
                    Text(
                        text = "CEP inválido",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                // Logradouro
                TextField(value = address.logradouro.orEmpty(),
                    onValueChange = {},
                    label = { Text("Logradouro") },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !isLoading
                )

                // Complemento
                TextField(value = complemento,
                    onValueChange = { complemento = it },
                    label = { Text("Número") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                // Bairro
                TextField(value = address.bairro.orEmpty(),
                    onValueChange = {},
                    label = { Text("Bairro") },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !isLoading
                )

                // Localidade
                TextField(value = address.localidade.orEmpty(),
                    onValueChange = {},
                    label = { Text("Localidade") },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !isLoading
                )

                // UF
                TextField(value = address.uf.orEmpty(),
                    onValueChange = {},
                    label = { Text("UF") },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !isLoading
                )

                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedButton(
                        onClick = onBack, modifier = Modifier.weight(1f)
                    ) {
                        Text("Cancelar")
                    }
                    Button(
                        onClick = {
                            if (cpf.length == 11 && cep.length == 8 && name.isNotBlank() && complemento.isNotBlank()) {
                                viewModel.updateCustomer(
                                    customer!!.copy(
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
                        }, modifier = Modifier.weight(1f), enabled = !isLoading
                    ) {
                        Text("Salvar")
                    }
                }

                OutlinedButton(
                    onClick = {
                        viewModel.deleteCustomer(customer!!)
                        onBack()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = MaterialTheme.colorScheme.error
                    ),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.error)
                ) {
                    Text("Deletar", color = MaterialTheme.colorScheme.error)
                }
            }
        }
    }
}


