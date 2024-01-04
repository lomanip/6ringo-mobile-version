package com.example.android_6ringo

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.android_6ringo.screens.SplashScreen


@Composable
fun NavGraph() {
    val navController = LocalComposeContext.current.navController

    NavHost(navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen()
        }
    }
}