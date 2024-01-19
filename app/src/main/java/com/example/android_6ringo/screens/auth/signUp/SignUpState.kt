package com.example.android_6ringo.screens.auth.signUp

import android.util.Log
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.example.android_6ringo.LocalComposeContext
import com.example.android_6ringo.auth.models.SignUpModel
import com.example.android_6ringo.auth.services.AuthService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import org.kodein.di.instance


@Composable
fun rememberSignUpState(): SignUpState {
    val services = LocalComposeContext.current.container.kodein
    val navController = LocalComposeContext.current.navController
    val infoSnackbarHost = LocalComposeContext.current.snackbarInfoHostState
    val coroutineScope = rememberCoroutineScope()
    val authService : AuthService by services.instance()
    return remember { SignUpState(authService, coroutineScope, navController, infoSnackbarHost) }
}

class SignUpState(
    var authService: AuthService,
    var coroutineScope: CoroutineScope,
    var navController: NavController,
    var infoSnackbarHost: SnackbarHostState
    ) {

    var email by mutableStateOf("")
    var userName by mutableStateOf("")
    var phone by mutableStateOf("")
    var password by mutableStateOf("")
    var passwordMatcher by mutableStateOf("")

    var passwordVisible by mutableStateOf(false)
    var signUpWaiting = mutableStateOf(false)



    suspend fun signUp(): Boolean{
        val model = SignUpModel()
        model.email = email
        model.username = userName
        model.password = password
        model.phone = phone


        signUpWaiting.value = true
        try {
            authService.signUp(model)
            signUpWaiting.value = false
            navController.navigate("home")
            delay(200)
            infoSnackbarHost.showSnackbar("Votre compte a été créer.", "Se connecter")
            return true
        }catch (ex: Exception) {
            signUpWaiting.value = false
            Log.e(this.javaClass.name, "Error during signUp", ex)
        }
        return false
    }
}