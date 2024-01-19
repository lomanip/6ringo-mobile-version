package com.example.android_6ringo.screens.shoppingCarts

import androidx.compose.runtime.Composable


@Composable
fun ShoppingCartListPage() {
    val shoppingCartListState = rememberShoppingCartState()
    ShoppingCartList(shoppingCartListState)
}