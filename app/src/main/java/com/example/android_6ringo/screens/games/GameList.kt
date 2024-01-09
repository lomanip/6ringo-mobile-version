package com.example.android_6ringo.screens.games

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.android_6ringo.R
import com.example.android_6ringo.ui.theme.CircularProgressSurface
import kotlinx.coroutines.launch

@Composable
fun GameList(state: GameListState) {
    LaunchedEffect(Unit) {
        state.loadPage()
    }

    val isAtBottom = !state.listState.canScrollForward
    LaunchedEffect(isAtBottom) {
        if(isAtBottom && !state.isPristine  && (state.total > state.games.size)) {
            state.loadPage()
        }
    }


    Column(Modifier.fillMaxWidth()) {
        val lazyRowState = rememberLazyListState()
        LazyRow(state = lazyRowState, modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            item {
                GameCategoryButton(selected = state.selectedCategory == "",
                    onChange = {
                        state.changeCategory("")
                        state.scope.launch { lazyRowState.animateScrollToItem(0) }
                    }) {
                    Text(text = "Tous")
                }
            }

            item {
                GameCategoryButton(selected = state.selectedCategory == "GOLD", onChange = {
                    state.changeCategory("GOLD")
                    state.scope.launch { lazyRowState.animateScrollToItem(1, 100) }
                }) {
                    Image(painterResource(id = R.drawable.gold_icon), "", modifier=Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Gold")
                }
            }

            item {
                GameCategoryButton(selected = state.selectedCategory == "PLATINIUM",
                    onChange = {
                        state.changeCategory("PLATINIUM")
                        state.scope.launch { lazyRowState.animateScrollToItem(2) }
                    }
                ) {
                    Image(painterResource(id = R.drawable.platinum_icon), "", modifier=Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Platinium")
                }
            }
            item {
                GameCategoryButton(selected = state.selectedCategory == "DIAMOND",
                    onChange = {
                        state.changeCategory("DIAMOND")
                        state.scope.launch { lazyRowState.animateScrollToItem(3) }
                    }) {
                    Image(painterResource(id = R.drawable.diamond_icon), "", modifier=Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Diamond")
                }
            }
        }


        LazyColumn(state = state.listState,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp)) {

            items(state.games, key = { it._id }) { game ->
                GameListItem(game)
            }

            item {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center) {
                    if (!state.isPristine && state.total == state.games.size) {
                        Spacer(
                            modifier = Modifier
                                .size(4.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .background(MaterialTheme.colorScheme.onBackground.copy(.5f))
                        )
                    }

                    if (state.loadingPage) {
                        CircularProgressIndicator(Modifier.size(32.dp))
                    }
                }
            }
        }
    }


}