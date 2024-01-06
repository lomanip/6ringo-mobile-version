package com.example.android_6ringo.screens.games

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun GameList(state: GameListState) {
    LaunchedEffect(Unit) {
        state.loadPage()
    }

    LazyColumn(state = state.listState) {
        items(state.games, key = { it._id }) { game ->
            GameListItem(game)
        }
    }
}