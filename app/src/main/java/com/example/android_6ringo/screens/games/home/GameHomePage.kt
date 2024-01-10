package com.example.android_6ringo.screens.games.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.android_6ringo.LocalComposeContext
import com.example.android_6ringo.helpers.LONG_DATE_FORMAT
import com.example.android_6ringo.helpers.formatGameStatus
import com.example.android_6ringo.helpers.getStatusColor
import com.example.android_6ringo.ui.theme.CircularProgressSurface
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GameHomePage(id: String) {
    val state = rememberGameHome(id)

    LaunchedEffect(Unit) {
        state.load()
    }

    GameHomeScaffold(state = state) {
        if(state.isLoading){
            CircularProgressSurface()
        }else {
            Column(Modifier.verticalScroll(rememberScrollState())) {
                Column(Modifier) {
                    val pagerState = rememberPagerState(pageCount = {state.images.size})
                    HorizontalPager(state=pagerState) {page ->
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .aspectRatio(1.61f)){
                            AsyncImage(state.images[page].thumbnailUrl,
                                "Article image",
                                contentScale = ContentScale.FillHeight,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.White))
                        }
                    }


                    Spacer(Modifier.height(8.dp))
                    val lazyRowState = rememberLazyListState()
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp), state = lazyRowState) {
                        items(items=state.images, key={ it.id }) {
                            val borderColor = if(state.selectedImage.id == it.id) MaterialTheme.colorScheme.primary else Color.Transparent
                            Box(
                                Modifier
                                    .width(100.dp)
                                    .aspectRatio(1.61f)
                                    .clip(RoundedCornerShape(4.dp))
                                    .border(2.dp, borderColor, shape = RoundedCornerShape(4.dp))
                                    .clickable {
                                        state.changeSelectedImage(it)
                                        state.scope.launch {
                                            pagerState.animateScrollToPage(state.images.indexOf(it))
                                            lazyRowState.animateScrollToItem(state.images.indexOf(it))
                                        }
                                    }
                            ) {
                                AsyncImage(it.thumbnailUrl, "", contentScale = ContentScale.FillHeight,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Color.White)
                                )
                            }
                        }
                    }
                }

                Column(Modifier) {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(16.dp)) {
                        Text(state.game.article?.name ?: "", style = MaterialTheme.typography.titleLarge)
                        Spacer(Modifier.height(8.dp))
                        Text("$${state.game.article?.price}",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = .7f)
                            )
                    }

                    Column(Modifier.padding(16.dp)) {
                        Row (verticalAlignment = Alignment.CenterVertically) {
                            Text(state.game.status.formatGameStatus(),
                                color = state.game.getStatusColor() ,
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(text = "•", fontSize = 22.sp, modifier=Modifier.padding(horizontal = 4.dp))
                            if(state.game.status.uppercase() == "ON_GOING") {
                                Text(text = "S'achève le ${state.game.endDate.format(LONG_DATE_FORMAT)}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onBackground.copy(.75f)
                                )
                            }else if(state.game.status.uppercase() == "UPCOMING") {
                                Text(text = "Débute le ${state.game.startDate.format(LONG_DATE_FORMAT)}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onBackground.copy(.75f)
                                )
                            }
                        }
                        Spacer(Modifier.height(8.dp))
                        Row(
                            Modifier
                                .fillMaxWidth()) {
                            Button(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth()) {
                                Text("Ajouter au panier $${state.game.price}")
                            }
                        }
                    }


                    Column(Modifier.padding(16.dp)) {
                        Text(state.game.article?.description ?: "", style=MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameHomeScaffold(state: GameHomeState, content: @Composable () -> Unit) {
    val navController = LocalComposeContext.current.navController
    Scaffold(topBar = {
        TopAppBar(
            title = {Text("Jeu",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis)},
            navigationIcon = { IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
            }}
        )
    }) {
        Column(Modifier.padding(it)) {
            content()
        }
    }
}