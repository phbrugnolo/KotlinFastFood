package com.example.fastfooda1.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "addresses",
    foreignKeys = [
        ForeignKey(
            entity = Customer::class,
            parentColumns = ["id"],
            childColumns = ["customerId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Address(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val customerId: Int,
    val cep: String,
    val logradouro: String,
    val complemento: String?,
    val bairro: String,
    val localidade: String,
    val uf: String,
    val estado: String,
    val erro: Boolean
)
