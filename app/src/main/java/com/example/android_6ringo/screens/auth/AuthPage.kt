package com.example.android_6ringo.screens.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.android_6ringo.LocalComposeContext
import com.example.android_6ringo.auth.services.AuthService
import org.kodein.di.instance

@Composable
fun AuthPage() {
    val navController = LocalComposeContext.current.navController
    val container = LocalComposeContext.current.container.kodein

    LaunchedEffect(Unit) {
        val authService: AuthService by container.instance()
        if(authService.isLoggedIn()){
            navController.navigate("auth/home") {
                popUpTo("auth")
            }
        }else{
            navController.navigate("auth/signIn") {
                popUpTo("auth")
            }
        }

    }
}