package com.example.android_6ringo.screens.games.home

import android.util.Log
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.example.android_6ringo.LocalComposeContext
import com.example.android_6ringo.auth.services.AuthService
import com.example.android_6ringo.entities.Game
import com.example.android_6ringo.entities.Image
import com.example.android_6ringo.services.GameService
import com.example.android_6ringo.services.ShoppingCartService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.kodein.di.instance

@Composable
fun rememberGameHome(id: String): GameHomeState {
    val services = LocalComposeContext.current.container.kodein
    val gameService: GameService by services.instance()
    val shoppingCartService: ShoppingCartService by services.instance()
    val authService: AuthService by services.instance()
    val infoSnackbarHost = LocalComposeContext.current.snackbarInfoHostState
    val scope = rememberCoroutineScope()
    return remember {
        GameHomeState(
            gameService,
            shoppingCartService,
            authService,
            infoSnackbarHost,
            scope,
            id
        )
    }
}

class GameHomeState(
    private var gameService: GameService,
    private var shoppingCartService: ShoppingCartService,
    private var authService: AuthService,
    var snackbarInfoHostState: SnackbarHostState,
    var scope: CoroutineScope,
    var id: String
) {
    internal val addToCartWaiting = mutableStateOf(false)
    var isLoading by mutableStateOf(true)


    var game by mutableStateOf(Game())
    var selectedImage by mutableStateOf(Image())
    val images get() = if (game.article?.images != null) game.article!!.images else listOf()


    suspend fun load() {
        try {
            isLoading = true
            game = gameService.get(id)
            if (game.article?.images != null && game.article?.images?.size!! > 0) {
                selectedImage = game.article!!.images[0]
            }
            isLoading = false
            Log.d(this.javaClass.name, "Load ${id} games.")
        } catch (e: Exception) {
            isLoading = false
            Log.e(GameService::javaClass.name, "Error during load game home page.", e)
        }
    }

    fun onAddToCartClick() {
        this.scope.launch {
            if(!authService.isLoggedIn()) {
                snackbarInfoHostState.showSnackbar("Connectez vous pour ajouter un jeu à votre panier.", "",
                    false, SnackbarDuration.Short)
            }else {
                addToCart()
            }
        }
    }
    private suspend fun addToCart() {
        addToCartWaiting.value = true
        try {
            val user = authService.user!!
            shoppingCartService.add(user, game)
            addToCartWaiting.value = false

            snackbarInfoHostState.showSnackbar(
                "Jeu ajouté au panier.",
                duration = SnackbarDuration.Short
            )
        } catch (ex: Exception) {
            addToCartWaiting.value = false
            Log.e(this.javaClass.name, "Error on add game from cart", ex)
        }
    }

    fun changeSelectedImage(it: Image) {
        selectedImage = it
    }
}