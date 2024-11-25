package com.example.fastfooda1.models

data class ViaCepResponse (
    val cep: String?,
    val logradouro: String?,
    val complemento: String?,
    val bairro: String?,
    val localidade: String?,
    val uf: String?,
    val estado: String?
)