package com.example.fastfooda1.models

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val price: BigDecimal,
    @DrawableRes val image: Int,
    val quantity: Int
)