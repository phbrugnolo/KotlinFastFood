package com.example.fastfooda1.util

import android.content.Context
import android.net.Uri
import java.io.File

fun saveImageToInternalStorage(context: Context, uri: Uri): String {
    val inputStream = context.contentResolver.openInputStream(uri)
    val fileName = "${System.currentTimeMillis()}.jpg"
    val file = File(context.filesDir, fileName)
    inputStream.use { input ->
        file.outputStream().use { output ->
            input?.copyTo(output)
        }
    }
    return file.absolutePath
}
