package com.example.android_6ringo.screens.auth.signIn

import android.util.Log
import androidx.compose.material.TextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavController
import com.example.android_6ringo.LocalComposeContext
import com.example.android_6ringo.auth.models.SignInModel
import com.example.android_6ringo.auth.services.AuthService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.kodein.di.instance


@Composable
fun rememberSignInState(): SignInState {
    val services = LocalComposeContext.current.container.kodein
    val navController = LocalComposeContext.current.navController
    val infoSnackbarHost = LocalComposeContext.current.snackbarInfoHostState
    val coroutineScope = rememberCoroutineScope()
    val authService : AuthService by services.instance()
    return remember { SignInState(authService, coroutineScope, navController, infoSnackbarHost) }
}

class SignInState(
    var authService: AuthService,
    var coroutineScope: CoroutineScope,
    var navController: NavController,
    var infoSnackbarHost: SnackbarHostState
    ) {

    var email by mutableStateOf("")
    var password by mutableStateOf("")

    var signInWaiting = mutableStateOf(false)



    suspend fun signIn(): Boolean{
        val model = SignInModel(email.trim(), password.trim())

        signInWaiting.value = true
        try {
            authService.signIn(model)
            signInWaiting.value = false
            navController.navigate("home")
            return true
        }catch (ex: Exception) {
            signInWaiting.value = false
            Log.e(this.javaClass.name, "Error during signIn", ex)
        }
        return false
    }
}