package com.example.android_6ringo.screens.home

import android.util.Log
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.example.android_6ringo.GameService
import com.example.android_6ringo.LocalComposeContext
import com.example.android_6ringo.entities.Game
import com.example.android_6ringo.models.PagingOptions
import kotlinx.coroutines.CoroutineScope
import org.kodein.di.instance

@Composable
fun rememberHomePageState(): HomePageState {
    val services = LocalComposeContext.current.container.kodein
    val gameService: GameService by services.instance()
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    return remember { HomePageState(scope, gameService) }
}

class HomePageState(
    var coroutineScope: CoroutineScope,
    var gameService: GameService
) {

    var hotGames by mutableStateOf(listOf<Game>())

    var isLoadingHotGame by mutableStateOf(false)

    suspend fun loadHotGames() {
        try {
            isLoadingHotGame = true
            val pageResult =
                gameService.paginate("", PagingOptions(orderBy = "createAt", limit = 5))
            hotGames = pageResult.data

            isLoadingHotGame = false

            Log.d(this.javaClass.name, "Load ${pageResult.data.size} hot games.")
        } catch (e: Exception) {
            isLoadingHotGame = false
            Log.e(GameService::javaClass.name, "Error during load hot game page.", e)
        }
    }

}