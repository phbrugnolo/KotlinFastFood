package com.example.fastfooda1.sampleData

import com.example.fastfooda1.R
import com.example.fastfooda1.models.Product
import java.math.BigDecimal

val sampleProducts = listOf(
    Product(
        name = "Hamburguer",
        price = BigDecimal("12.99"),
        image = R.drawable.ic_launcher_background,
        quantity = 50
    ),
    Product(
        name = "Pizza",
        price = BigDecimal("19.99"),
        image = R.drawable.ic_launcher_foreground,
        quantity = 15
    ),
    Product(
        name = "Batata frita",
        price = BigDecimal("7.99"),
        image = R.drawable.ic_launcher_background,
        quantity = 76
    )
)