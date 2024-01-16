package com.example.android_6ringo.ui.theme

import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

val ButtonDefaults.errorButtonColors: ButtonColors
    @Composable
    get() = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error,
        contentColor = MaterialTheme.colorScheme.onError)

val ButtonDefaults.secondaryButtonColors:ButtonColors
    @Composable
    get() = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary,
        contentColor = MaterialTheme.colorScheme.onSecondary)