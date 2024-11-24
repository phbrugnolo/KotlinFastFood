package com.example.fastfooda1.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customers")
data class Customer (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val cpf: String
)