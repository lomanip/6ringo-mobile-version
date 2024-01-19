package com.example.android_6ringo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.ConfirmationNumber
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MonetizationOn
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.example.android_6ringo.screens.SplashScreen
import com.example.android_6ringo.screens.auth.AuthHomePage
import com.example.android_6ringo.screens.auth.AuthPage
import com.example.android_6ringo.screens.auth.signIn.SignInPage
import com.example.android_6ringo.screens.auth.signUp.SignUpPage
import com.example.android_6ringo.screens.games.GameListPage
import com.example.android_6ringo.screens.games.home.GameHomePage
import com.example.android_6ringo.screens.home.HomePage
import com.example.android_6ringo.screens.shoppingCarts.ShoppingCartListPage

val GAME_LIST_ROUTE= "games/list?category={category}"
@Composable
fun NavGraph() {
    val navController = LocalComposeContext.current.navController


    Column(modifier = Modifier.fillMaxSize()) {
        NavHost(navController, startDestination = "splash",
            modifier = Modifier.weight(1f)) {
            composable("splash") {
                SplashScreen()
            }

            composable("home") {
                HomePage()
            }

            composable(
                GAME_LIST_ROUTE,
                arguments = listOf(navArgument("category") {
                    defaultValue = ""
                    type = NavType.StringType })
                ) {
                val category = it.arguments!!.getString("category") ?: ""
                GameListPage(category)
            }

            composable("games/{gameId}") {
                val gameId = it.arguments!!.getString("gameId") ?: ""
                GameHomePage(gameId)
            }

            composable("cart"){
                ShoppingCartListPage()
            }

            composable("auth"){
                AuthPage()
            }

            composable("auth/home") {
                AuthHomePage()
            }

            composable("auth/signIn") {
                SignInPage()
            }

            composable("auth/signUp") {
                SignUpPage()
            }
        }

        NavigationBar(Modifier.fillMaxWidth()) {
            val currentBackStackEntry = navController.currentBackStackEntryAsState()
            val route = currentBackStackEntry.value?.destination?.route
            NavigationBarItem(selected = currentBackStackEntry.value?.destination?.route == "home",
                onClick = { navController.navigate("home") },
                icon = { Icon(imageVector = Icons.Outlined.Home, contentDescription = "") },
                label = {Text("Acceuil")}
            )

            NavigationBarItem(
                selected = route?.startsWith("games/") ?: false,
                onClick = { navController.navigate("games/list") },
                icon = { Icon(imageVector = Icons.Outlined.MonetizationOn, contentDescription = "") },
                label = {Text("Jeux")}
            )



            NavigationBarItem(route?.startsWith("tickets/") ?: false, onClick = { /*TODO*/ },
                icon = { Icon(imageVector = Icons.Outlined.ConfirmationNumber, contentDescription = "") },
                label = {Text("Tickets")}
            )

            NavigationBarItem(route?.startsWith("cart/") ?: false,
                onClick = { navController.navigate("cart") },
                icon = { Icon(imageVector = Icons.Outlined.Star, contentDescription = "") },
                label = {Text("Panier")}
            )

            NavigationBarItem(selected = route?.startsWith("auth/") ?: false,
                onClick = { navController.navigate("auth") },
                icon = { Icon(imageVector = Icons.Outlined.AccountCircle, contentDescription = "") },
                label = { Text("Compte") }
            )
        }
    }
}