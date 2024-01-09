package com.example.android_6ringo.screens.home

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.example.android_6ringo.GameService
import com.example.android_6ringo.LocalComposeContext
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
class HomePageState(var coroutineScope: CoroutineScope,
                    var gameService: GameService) {

}