package com.example.android_6ringo.screens.auth.signUp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.android_6ringo.LocalComposeContext
import com.example.android_6ringo.R
import com.example.android_6ringo.ui.theme.AlertLoading
import kotlinx.coroutines.launch


@Composable
fun SignUpPage() {
    val state = rememberSignUpState()
    val focusRequester = remember { FocusRequester() }
    Column(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(64.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Image(painter = painterResource(id = R.drawable.six_ringo_logo), "", modifier = Modifier.size(128.dp))
        }

        Spacer(modifier = Modifier.height(32.dp))
        Column(Modifier.fillMaxWidth()) {
            Text("Créer un compte", style = MaterialTheme.typography.titleLarge, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = state.email,
                onValueChange = { state.email = it.trim() },
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                label = { Text("Email*") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = state.userName,
                onValueChange = { state.userName = it.trim() },
                modifier = Modifier
                    .fillMaxWidth(),
                label = { Text("Nom d'utilisateur") },
                singleLine = true
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = state.phone,
                onValueChange = { state.phone = it.trim() },
                modifier = Modifier
                    .fillMaxWidth(),
                label = { Text("Numéro de téléphone") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = state.password,
                onValueChange = {state.password = it},
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Mot de passe*") },
                singleLine = true,
                visualTransformation = if (state.passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image = if (state.passwordVisible)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff

                    // Please provide localized description for accessibility services
                    val description = if (state.passwordVisible) "Hide password" else "Show password"

                    IconButton(onClick = {state.passwordVisible = !state.passwordVisible}){
                        Icon(imageVector  = image, description)
                    }
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = state.passwordMatcher,
                onValueChange = {state.passwordMatcher = it},
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Confirmer le mot de passe*") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = if (state.passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),

                )
            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = { state.coroutineScope.launch { state.signUp() } },
                modifier = Modifier.fillMaxWidth()) {
                Text(text = "Créer un compte")
            }

            Divider(Modifier.padding(vertical = 24.dp))

            Text(text = "Vous possédez déjà un compte ?")
            Spacer(modifier = Modifier.height(8.dp))
            val navController = LocalComposeContext.current.navController
            OutlinedButton(onClick = { navController.navigate("auth/signIn") },
                modifier = Modifier.fillMaxWidth()) {
                Text(text = "Se connecter")
            }
        }
        
        AlertLoading(state = state.signUpWaiting) {
            Text(text = "Création du compte en cours.")
        }
    }

    LaunchedEffect(Unit){
        focusRequester.requestFocus()
    }
}