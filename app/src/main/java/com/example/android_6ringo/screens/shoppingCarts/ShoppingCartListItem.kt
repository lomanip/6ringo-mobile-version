package com.example.android_6ringo.screens.shoppingCarts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.android_6ringo.entities.ShoppingCart


@Composable
fun ShoppingCartListItem(modifier: Modifier = Modifier, shoppingCart: ShoppingCart, onDelete: () -> Unit) {
    Box(modifier = Modifier) {
        Row(Modifier.fillMaxWidth()) {
            Text(text = shoppingCart.game?.article?.name ?: "", style = MaterialTheme.typography.bodyMedium)
        }
    }
}