package com.example.android_6ringo.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun CircularProgressSurface(size: Dp = 48.dp) {
    Box(
        Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.background)
        .padding(32.dp),
        contentAlignment = Alignment.Center

    ) {
        CircularProgressIndicator(Modifier.size(size))
    }
}