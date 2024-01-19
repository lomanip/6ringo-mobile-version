package com.example.android_6ringo.screens.shoppingCarts

import android.util.Log
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.example.android_6ringo.LocalComposeContext
import com.example.android_6ringo.auth.models.User
import com.example.android_6ringo.auth.services.AuthService
import com.example.android_6ringo.entities.ShoppingCart
import com.example.android_6ringo.services.ShoppingCartService
import kotlinx.coroutines.CoroutineScope
import org.kodein.di.instance


@Composable
fun rememberShoppingCartState(): ShoppingCartListState {
    val services = LocalComposeContext.current.container.kodein
    val shoppingCartService: ShoppingCartService by services.instance()
    val authService: AuthService by services.instance()
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    var user: User? = null

    if(authService.isLoggedIn()){
        user = authService.user
    }
    return remember { ShoppingCartListState(scope, shoppingCartService, listState, user) }
}
class ShoppingCartListState(
    var coroutineScope: CoroutineScope,
    var shoppingCartService: ShoppingCartService,
    var listState: LazyListState,
    var user: User?
) {
    var shoppingCarts by mutableStateOf(listOf<ShoppingCart>())
    var isLoading by mutableStateOf(false)
    var isPristine by mutableStateOf(true)

    fun remove(shoppingCart: ShoppingCart) {
        shoppingCarts = shoppingCarts.filter { it != shoppingCart }
    }

    suspend fun load() {
        try {
            isLoading = true
            val items = shoppingCartService.list(user!!)
            shoppingCarts = items

            isLoading = false
            isPristine = false
            Log.d(this.javaClass.name, "Load ${items.size} shoppingCarts.")
        }catch (e: Exception) {
            isLoading = false
            Log.e(ShoppingCartService::javaClass.name, "Error during load shoppingCart page.", e)
        }
    }

    suspend fun reset() {
        isPristine = true
        shoppingCarts = listOf()
        load()
    }

}