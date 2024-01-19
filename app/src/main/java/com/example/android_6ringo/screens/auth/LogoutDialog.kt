package com.example.android_6ringo.screens.auth

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.android_6ringo.ComposeContext
import com.example.android_6ringo.Container
import com.example.android_6ringo.LocalComposeContext
import com.example.android_6ringo.auth.services.AuthService
import com.example.android_6ringo.ui.theme.AlertLoading
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.kodein.di.instance


@Composable
fun rememberSignOutViewModel(onSave: () -> Unit): SignOutViewModel {
    val composeContext = LocalComposeContext.current
    return viewModel {SignOutViewModel(composeContext, onSave)}
}
class SignOutViewModel(var composeContext: ComposeContext,
                       var onSave: () -> Unit): ViewModel() {

    private val authService: AuthService by composeContext.container.kodein.instance()
    internal var state = mutableStateOf(false)
    internal val logoutWaiting = mutableStateOf(false)
    fun close() {
        state.value = false
    }

    fun open() {
        state.value = true
    }


    suspend fun logout() {
        logoutWaiting.value = true
        try {
            authService.signOut()
            logoutWaiting.value = false
            onSave()
            close()
            delay(500)
            composeContext.snackbarInfoHostState.showSnackbar("Vous êtes maintenant déconnecté.", duration = SnackbarDuration.Short)
        } catch (ex: Exception) {
            logoutWaiting.value = false
            Log.e(this.javaClass.name, "Error on delete stock", ex)
        }
    }
}

@Composable
fun SignOutDialog(viewModel: SignOutViewModel) {


    AlertLoading(state = viewModel.logoutWaiting) {
        Text(text = "Déconnexion en cours.", style = MaterialTheme.typography.bodyMedium)
    }

    if(viewModel.state.value) {
        Dialog(onDismissRequest = { viewModel.close() }) {
            Surface(color = MaterialTheme.colorScheme.surface) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Image(
                        Icons.Filled.Logout,
                        contentDescription = "",
                        modifier = Modifier.size(64.dp)
                    )

                    Spacer(Modifier.height(16.dp))
                    Text(
                        text = "Se déconnecter",
                        style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold
                    )
                    Spacer(Modifier.height(16.dp))
                    Text(
                        text = "Voulez-vous vous déconnecter maintenant ?",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.alpha(0.8f)
                    )

                    Spacer(Modifier.height(16.dp))

                    Row(horizontalArrangement = Arrangement.Center) {
                        TextButton(
                            onClick = { viewModel.close() },
                        ) {
                            Text(text = "Annuler".uppercase())
                        }
                        Spacer(Modifier.width(16.dp))
                        FilledTonalButton(onClick = {
                            viewModel.viewModelScope.launch {
                                viewModel.logout()
                            }
                        }, colors = ButtonDefaults.filledTonalButtonColors(contentColor = MaterialTheme.colorScheme.error)) {
                            Text(text = "Se déconnecter".uppercase())
                        }
                    }
                }
            }
        }
    }
}