package com.example.android_6ringo.screens.games

import android.util.Log
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.example.android_6ringo.GameService
import com.example.android_6ringo.LocalComposeContext
import com.example.android_6ringo.entities.Game
import com.example.android_6ringo.models.PagingOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.kodein.di.instance

@Composable
fun rememberGameList(): GameListState {
    val services = LocalComposeContext.current.container.kodein
    val gameService: GameService by services.instance()
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    return remember { GameListState(gameService, listState, scope) }
}

class GameListState(private var gameService: GameService,
                    var listState: LazyListState,
                    var scope: CoroutineScope
    ) {
    var games by mutableStateOf(listOf<Game>())
    var loadingPage by mutableStateOf(false)
    var isPristine by mutableStateOf(true)

    var pageSize = 4
    var total by mutableIntStateOf(0)
    var currentIndex by mutableIntStateOf(0)
    var currentPage by mutableIntStateOf(0)
    var hasNextPage by mutableStateOf(true)

    var selectedCategory by mutableStateOf("")

    suspend fun loadPage() {
        try {
            loadingPage = true
            val pageResult = gameService.paginate(this.selectedCategory, PagingOptions(orderBy = "createAt", page = currentPage+1, limit = pageSize))
            games = games + pageResult.data
            total = pageResult.metaData.total
            currentPage += 1
            hasNextPage = pageResult.metaData.hasNextPage

            loadingPage = false
            isPristine = false
            Log.d(this.javaClass.name, "Load ${pageResult.data.size} games.")
        }catch (e: Exception) {
            loadingPage = false
            Log.e(GameService::javaClass.name, "Error during load game page.", e)
        }
    }

    fun changeCategory(category: String) {
        this.scope.launch {
            selectedCategory = category
            reset()
            Log.d(this@GameListState::javaClass.name, "Load new category with ${games.size} games.")
        }
    }

    suspend fun reset() {
        isPristine = true
        currentPage = 0
        games = listOf()
        loadPage()
    }
}