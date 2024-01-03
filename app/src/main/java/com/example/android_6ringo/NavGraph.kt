package com.example.android_6ringo

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost


@Composable
fun NavGraph() {
    val navController = LocalComposeContext.current.navController

    NavHost(navController, startDestination = "splash") {

    }
}