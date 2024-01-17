package com.example.android_6ringo.entities

data class ShoppingCart(var gameId: String, var game: Game?, var quantity: Int = 0)
data class ShoppingCartAddResult(var userId: String, var items: List<ShoppingCart>)
