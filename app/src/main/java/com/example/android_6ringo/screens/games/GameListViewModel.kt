package com.example.android_6ringo.screens.games

import android.util.Log
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.android_6ringo.GameService
import com.example.android_6ringo.LocalComposeContext
import com.example.android_6ringo.entities.Game
import org.kodein.di.instance

@Composable
fun rememberGameList(): GameListState {
    val services = LocalComposeContext.current.container.kodein
    val gameService: GameService by services.instance()
    val listState = rememberLazyListState()
    return remember { GameListState(gameService, listState) }
}

class GameListState(private var gameService: GameService, var listState: LazyListState) {
    var games by mutableStateOf(listOf<Game>())

    suspend fun loadPage() {
        try {
            val pageResult = gameService.paginate()
            games = games + pageResult.data
        }catch (e: Exception) {
            Log.e(GameService::javaClass.name, "Error during load game page.", e)
        }
    }
}