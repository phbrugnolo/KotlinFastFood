package com.example.fastfooda1.util

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun MaskedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    mask: String,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    var rawText by remember { mutableStateOf(value.filter { it.isDigit() }) }

    TextField(
        value = applyMask(mask, rawText),
        onValueChange = { newText ->
            val digits = newText.filter { it.isDigit() }
            rawText = digits
            onValueChange(digits)
        },
        label = { Text(label) },
        isError = isError,
        enabled = enabled,
        keyboardOptions = keyboardOptions,
        modifier = modifier
    )
}

fun applyMask(mask: String, input: String): String {
    val result = StringBuilder()
    var inputIndex = 0
    for (char in mask) {
        if (inputIndex >= input.length) break
        result.append(if (char == '#') input[inputIndex++] else char)
    }
    return result.toString()
}

