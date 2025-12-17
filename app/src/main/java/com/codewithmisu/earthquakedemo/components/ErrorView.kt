package com.codewithmisu.earthquakedemo.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ErrorView(message: String, onRetry: () -> Unit = {}) {
    Box(
        modifier = Modifier.padding(16.dp), contentAlignment = Alignment.Center
    ) {
        Column {
            Icon(Icons.Default.ErrorOutline, contentDescription = "Error Message")
            Text(message, fontWeight = FontWeight.W600)
            Button(onClick = onRetry) {
                Text("RETRY", fontWeight = FontWeight.W600)
            }
        }
    }
}