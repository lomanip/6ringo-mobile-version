package com.example.android_6ringo.screens.games

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.android_6ringo.entities.Game

@Composable
fun GameListItem (game: Game){
    Text(game.name)
}