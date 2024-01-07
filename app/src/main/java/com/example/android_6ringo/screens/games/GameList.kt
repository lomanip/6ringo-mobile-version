package com.example.android_6ringo.screens.games

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GameList(state: GameListState) {
    LaunchedEffect(Unit) {
        state.loadPage()
    }

    LazyColumn(state = state.listState,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(horizontal = 8.dp)) {
        items(state.games, key = { it._id }) { game ->
            GameListItem(game)
        }
    }
}