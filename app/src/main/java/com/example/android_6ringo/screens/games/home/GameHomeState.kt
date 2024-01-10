package com.example.android_6ringo.screens.games.home

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.example.android_6ringo.GameService
import com.example.android_6ringo.LocalComposeContext
import com.example.android_6ringo.entities.Game
import com.example.android_6ringo.entities.Image
import kotlinx.coroutines.CoroutineScope
import org.kodein.di.instance

@Composable
fun rememberGameHome(id: String): GameHomeState {
    val services = LocalComposeContext.current.container.kodein
    val gameService: GameService by services.instance()
    val scope = rememberCoroutineScope()
    return remember { GameHomeState(gameService, scope, id) }
}

class GameHomeState(private var gameService: GameService,
                    var scope: CoroutineScope,
                    var id: String
) {
    var isLoading by mutableStateOf(true)
    var game by mutableStateOf(Game())
    var selectedImage by mutableStateOf(Image())
    val images get() = if(game.article?.images != null) game.article!!.images else listOf()

    suspend fun load() {
        try {
            isLoading = true
            game = gameService.get(id)
            if(game.article?.images != null && game.article?.images?.size!! > 0) {
                selectedImage = game.article!!.images[0]
            }
            isLoading = false
            Log.d(this.javaClass.name, "Load ${id} games.")
        }catch (e: Exception) {
            isLoading = false
            Log.e(GameService::javaClass.name, "Error during load game home page.", e)
        }
    }

    fun changeSelectedImage(it: Image) {
        selectedImage = it
    }
}