package com.example.android_6ringo.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.android_6ringo.LocalComposeContext
import com.example.android_6ringo.entities.Game
import com.example.android_6ringo.helpers.LONG_DATE_FORMAT
import com.example.android_6ringo.helpers.formatGameStatus
import com.example.android_6ringo.helpers.getStatusColor
import com.example.android_6ringo.ui.theme.CircularProgressSurface

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeHotGames(state: HomePageState) {
    LaunchedEffect(Unit) {
        state.loadHotGames()
    }

    Column(Modifier.fillMaxWidth()) {
        Spacer(Modifier.height(8.dp))
        Box(Modifier.fillMaxWidth()) {
            if(state.isLoadingHotGames) {
                CircularProgressSurface()
            }else {
                val pagerState = rememberPagerState(pageCount = {state.hotGames.size})

                HorizontalPager(state = pagerState) {page ->
                    val game = state.hotGames[page]
                    HotGameSlide(game)
                }

                Box(
                    Modifier
                        .align(Alignment.TopStart)
                        .clip(RoundedCornerShape(0.dp, 0.dp, 18.dp, 0.dp))
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(16.dp, 8.dp)
                ) {
                    Text("Hot",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                }

                PagerThumbsBox(pagerState = pagerState, modifier=Modifier.fillMaxWidth()
                    .align(Alignment.BottomCenter).padding(8.dp))
            }
        }
    }
}


@Composable
fun HotGameSlide(game: Game) {
    val navController = LocalComposeContext.current.navController
    Box(
        Modifier
            .fillMaxWidth()
            .aspectRatio(1.61f).clickable {
                navController.navigate("games/${game._id}")
            }) {
        if(game.article != null && game.article!!.images.isNotEmpty()) {
            AsyncImage(game.article!!.images[0].thumbnailUrl, "",
                contentScale= ContentScale.FillHeight,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            )
        }

        Column(
            Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(
                    Color.Black.copy(alpha = .5f)
                )
                .padding(start=16.dp, end=16.dp, top=8.dp, bottom = 24.dp)) {
            Text(text = game.article?.name?.trim() ?: "",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = game.article?.description?.trim() ?: "",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = .9f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            //Spacer(modifier = Modifier.height(4.dp))

            Row (verticalAlignment = Alignment.CenterVertically) {
                Text(game.status.formatGameStatus(),
                    color = game.getStatusColor() ,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Text(text = "•", fontSize = 22.sp, modifier=Modifier.padding(horizontal = 4.dp))
                if(game.status.uppercase() == "ON_GOING") {
                    Text(text = "S'achève le ${game.endDate.format(LONG_DATE_FORMAT)}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onBackground.copy(.75f)
                    )
                }else if(game.status.uppercase() == "UPCOMING") {
                    Text(text = "Débute le ${game.startDate.format(LONG_DATE_FORMAT)}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onBackground.copy(.75f)
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerThumbsBox(pagerState: PagerState, modifier: Modifier = Modifier) {
    Box(modifier =modifier) {
        Row(modifier=Modifier.align(Alignment.Center), horizontalArrangement = Arrangement.spacedBy(4.dp)) {

            for (index in 0..<pagerState.pageCount) {
                val bgColor = if(pagerState.currentPage == index) Color.White else Color.White.copy(alpha = .3f)
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(bgColor)
                )
            }
        }
    }
}