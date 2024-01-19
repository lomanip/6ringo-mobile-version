package com.example.android_6ringo.entities

class ShoppingCart {
    var gameId: String = ""
    var game: Game? = null
    var quantity: Int = 0
}
class ShoppingCartAddResult() {
    var userId: String = ""
    var items: List<ShoppingCart> = listOf<ShoppingCart>()
}
