package com.example.fastfooda1.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customers")
data class Customer (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val cpf: String,
    val cep: String,
    val logradouro: String?,
    val complemento: String,
    val bairro: String?,
    val localidade: String?,
    val uf: String?,
    val estado: String?
)