package com.example.android_6ringo.screens.home

import android.util.Log
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.example.android_6ringo.LocalComposeContext
import com.example.android_6ringo.entities.Game
import com.example.android_6ringo.models.PagingOptions
import com.example.android_6ringo.services.GameService
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
    var upcomingGames by mutableStateOf(listOf<Game>())

    var isLoadingHotGames by mutableStateOf(false)
    var isLoadingUpcomingGames by mutableStateOf(false)


    suspend fun loadHotGames() {
        try {
            isLoadingHotGames = true
            val pageResult =
                gameService.paginate("", PagingOptions(orderBy = "createAt", limit = 5))
            hotGames = pageResult.data

            isLoadingHotGames = false

            Log.d(this.javaClass.name, "Load ${pageResult.data.size} hot games.")
        } catch (e: Exception) {
            isLoadingHotGames = false
            Log.e(GameService::javaClass.name, "Error during load hot game page.", e)
        }
    }


    suspend fun loadUpcomingGames() {
        try {
            isLoadingUpcomingGames = true
            val pageResult =
                gameService.paginate("", PagingOptions(orderBy = "createAt", limit = 5))
            upcomingGames = pageResult.data

            isLoadingHotGames = false
            Log.d(this.javaClass.name, "Load ${pageResult.data.size} upcoming games.")
        } catch (e: Exception) {
            isLoadingHotGames = false
            Log.e(GameService::javaClass.name, "Error during load upcoming game page.", e)
        }
    }

}