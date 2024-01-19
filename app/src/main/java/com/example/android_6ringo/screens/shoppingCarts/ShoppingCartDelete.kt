package com.example.android_6ringo.screens.shoppingCarts

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
import com.example.android_6ringo.LocalComposeContext
import com.example.android_6ringo.R
import com.example.android_6ringo.auth.services.AuthService
import com.example.android_6ringo.entities.ShoppingCart
import com.example.android_6ringo.services.ShoppingCartService
import com.example.android_6ringo.ui.theme.AlertLoading
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.kodein.di.instance

@Composable
fun rememberShoppingCartDeleteState(shoppingCart: ShoppingCart, onSave: (shoppingCart: ShoppingCart) -> Unit): ShoppingCartDeleteState {
    val composeContext = LocalComposeContext.current
    return viewModel {ShoppingCartDeleteState(composeContext, shoppingCart, onSave)}
}
class ShoppingCartDeleteState(val composeContext: ComposeContext,
                                  var shoppingCart: ShoppingCart,
                                  var onSave: (shoppingCart: ShoppingCart) -> Unit): ViewModel() {

    private val shoppingCartService: ShoppingCartService by composeContext.container.kodein.instance()
    private val authService: AuthService by composeContext.container.kodein.instance()
    internal var state = mutableStateOf(false)
    internal val deleteShoppingCartWaiting = mutableStateOf(false)
    fun close() {
        state.value = false
    }

    fun open() {
        state.value = true
    }


    suspend fun delete() {
        deleteShoppingCartWaiting.value = true
        try {
            val user = authService.user!!
            shoppingCartService.delete(user, shoppingCart.game!!)
            deleteShoppingCartWaiting.value = false
            onSave(shoppingCart)
            close()
            delay(500)
            composeContext.snackbarInfoHostState.showSnackbar("Jeu supprimé du panier.", duration = SnackbarDuration.Short)
        } catch (ex: Exception) {
            deleteShoppingCartWaiting.value = false
            Log.e(this.javaClass.name, "Error on delete game from cart", ex)
        }
    }
}

@Composable
fun ShoppingCartDeleteDialog(viewModel: ShoppingCartDeleteState) {
    val shoppingCart = viewModel.shoppingCart

    AlertLoading(state = viewModel.deleteShoppingCartWaiting) {
        Text(text = "Suppréssion en cours.", style = MaterialTheme.typography.bodyMedium)
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
                        painter = painterResource(R.drawable.delete),
                        contentDescription = "",
                        modifier = Modifier.size(64.dp)
                    )

                    Spacer(Modifier.height(16.dp))
                    Text(
                        text = "Supprimer ce jeu du panier",
                        style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold
                    )
                    Spacer(Modifier.height(16.dp))
                    Text(
                        text = "Voulez-vous supprimer ce jeux de votre panier ?",
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
                                viewModel.delete()
                            }
                        }, colors = ButtonDefaults.filledTonalButtonColors(contentColor = MaterialTheme.colorScheme.error)) {
                            Text(text = "Supprimer".uppercase())
                        }
                    }
                }
            }
        }
    }
}