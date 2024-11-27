package com.example.fastfooda1.util

import java.text.SimpleDateFormat
import java.util.Locale

fun formatDate(dateInMillis: Long): String {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale("pt", "BR"))
    return dateFormat.format(dateInMillis)
}
