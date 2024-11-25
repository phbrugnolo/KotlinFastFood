package com.example.fastfooda1.ui.screens.customer

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fastfooda1.viewmodels.CustomersViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditCustomerScreen(
    viewModel: CustomersViewModel,
    customerId: Int,
    onBack: () -> Unit
) {
    val customer by viewModel.getCustomer(customerId).collectAsState(initial = null)

    if (customer != null) {
        var name by remember { mutableStateOf(customer!!.name) }
        var cpf by remember { mutableStateOf(customer!!.cpf) }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Editar Cliente") },
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
                        }
                    },
                    colors = TopAppBarDefaults.mediumTopAppBarColors()
                )
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Nome") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = cpf,
                    onValueChange = { cpf = it },
                    label = { Text("CPF") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedButton(
                        onClick = onBack,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Cancelar")
                    }
                    Button(
                        onClick = {
                            viewModel.updateCustomer(
                                customer!!.copy(
                                    name = name,
                                    cpf = cpf
                                )
                            )
                            onBack()
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Salvar")
                    }
                }

                // Botão de deletar
                Spacer(modifier = Modifier.height(8.dp))
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

