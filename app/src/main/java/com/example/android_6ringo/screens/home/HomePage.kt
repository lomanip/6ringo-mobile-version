package com.example.android_6ringo.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.android_6ringo.LocalComposeContext
import com.example.android_6ringo.R
import com.example.android_6ringo.ui.theme.DiamondColor
import com.example.android_6ringo.ui.theme.GoldColor
import com.example.android_6ringo.ui.theme.OrangeDark
import com.example.android_6ringo.ui.theme.OrangeLight
import com.example.android_6ringo.ui.theme.PlatiniumColor

@Composable
fun HomePage() {
    val navController = LocalComposeContext.current.navController
    val state = rememberHomePageState()
    Column(Modifier.fillMaxSize()) {
        HomeBanner()
        Spacer(modifier = Modifier.height(32.dp))
        HomeHotGames(state)
    }
}