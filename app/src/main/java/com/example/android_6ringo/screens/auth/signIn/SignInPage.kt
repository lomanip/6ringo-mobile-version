package com.example.android_6ringo.screens.auth.signIn

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.android_6ringo.R
import com.example.android_6ringo.ui.theme.AlertLoading
import com.example.android_6ringo.ui.theme.secondaryButtonColors
import kotlinx.coroutines.launch


@Composable
fun SignInPage() {
    val state = rememberSignInState()
    val focusRequester = remember { FocusRequester() }
    Column(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)) {
        Spacer(modifier = Modifier.height(64.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Image(painter = painterResource(id = R.drawable.six_ringo_logo), "", modifier = Modifier.size(128.dp))
        }

        Spacer(modifier = Modifier.height(32.dp))
        Column(Modifier.fillMaxWidth()) {
            Text("Se connecter", style = MaterialTheme.typography.titleLarge, textAlign = TextAlign.Center)
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
                value = state.password,
                onValueChange = {state.password = it},
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Mot de passe*") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            )
            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = { state.coroutineScope.launch { state.signIn() } },
                modifier = Modifier.fillMaxWidth()) {
                Text(text = "Se connecter")
            }

            Divider(Modifier.padding(vertical = 24.dp))

            Text(text = "Vous n'avez pas de compte ?")
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedButton(onClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth()) {
                Text(text = "Créer un compte")
            }
        }
        
        AlertLoading(state = state.signInWaiting) {
            Text(text = "Connexion en cours.")
        }
    }

    LaunchedEffect(Unit){
        focusRequester.requestFocus()
    }
}