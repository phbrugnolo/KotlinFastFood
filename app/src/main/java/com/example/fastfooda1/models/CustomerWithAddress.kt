package com.example.fastfooda1.models

import androidx.room.Embedded
import androidx.room.Relation

data class CustomerWithAddress(
    @Embedded val customer: Customer,
    @Relation(
        parentColumn = "id",
        entityColumn = "customerId"
    )
    val address: Address?
)
