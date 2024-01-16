package com.example.android_6ringo.screens.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.android_6ringo.LocalComposeContext

@Composable
fun AuthPage() {
    val navController = LocalComposeContext.current.navController

    LaunchedEffect(Unit) {
        navController.navigate("auth/signIn") {
            popUpTo("auth")
        }
    }
}