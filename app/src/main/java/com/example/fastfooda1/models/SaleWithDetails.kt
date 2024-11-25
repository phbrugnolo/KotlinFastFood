package com.example.fastfooda1.models

import androidx.room.Embedded
import androidx.room.Relation

data class SaleWithDetails(
    @Embedded val sale: Sale,
    @Relation(
        parentColumn = "customerId",
        entityColumn = "id"
    )
    val customer: Customer,
    @Relation(
        parentColumn = "productId",
        entityColumn = "id"
    )
    val product: Product
)
