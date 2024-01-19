package com.example.android_6ringo.screens.shoppingCarts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.android_6ringo.LocalComposeContext
import com.example.android_6ringo.R
import kotlinx.coroutines.launch


@Composable
fun ShoppingCartList(state: ShoppingCartListState) {
    LaunchedEffect(Unit) {
        if(state.user != null){
            state.load()
        }
    }



    Column(Modifier.fillMaxWidth()) {
        LazyColumn(state = state.listState) {

            item {
                Spacer(Modifier.height(16.dp))
            }

            items(state.shoppingCarts, key = { it.game?.id ?: ""  }) { shoppingCart ->
                val navController = LocalComposeContext.current.navController
                ShoppingCartListItem(Modifier.fillMaxWidth().clickable {
                    navController.navigate("games/${shoppingCart.game?.id}")
                }, shoppingCart) {
                    state.remove(shoppingCart)
                }
            }

            item {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center) {
                    if (!state.isPristine && !state.isLoading) {
                        Spacer(
                            modifier = Modifier
                                .size(4.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .background(MaterialTheme.colorScheme.onBackground.copy(.5f))
                        )
                    }

                    if (state.isLoading) {
                        CircularProgressIndicator(Modifier.size(32.dp))
                    }
                }
            }
        }
    }


}