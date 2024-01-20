package com.example.android_6ringo.screens.auth.signUp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun rememberPasswordState(): PasswordState {
    return remember { PasswordState() }
}

class PasswordState() {
    var hasLowerCase by mutableStateOf(false)
    var hasUpperCase by mutableStateOf(false)
    var hasDigit by mutableStateOf(false)
    var hasSpecialChar by mutableStateOf(false)

    var password: String = ""
        get() {
            return field
        }
        set(value) {
            field = value
            hasDigit = value.any { it.isDigit() }
            hasLowerCase = value.any { it.isLowerCase() }
            hasUpperCase = value.any { it.isUpperCase() }
            hasSpecialChar = value.any { "!@#\$%^&*".contains(it) }
        }
}