package com.example.android_6ringo.screens.shoppingCarts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.android_6ringo.LocalComposeContext
import com.example.android_6ringo.screens.games.GameList
import com.example.android_6ringo.screens.games.rememberGameList


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingCartListPage() {

    val shoppingCartListState = rememberShoppingCartState()
    val navController = LocalComposeContext.current.navController
    Scaffold(topBar = {
        TopAppBar(title = { Text("Paniers (${shoppingCartListState.shoppingCarts.size})") },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
                }
            })
    }) {

        Column(Modifier.padding(it)) {
            ShoppingCartList(shoppingCartListState)
        }
    }
}