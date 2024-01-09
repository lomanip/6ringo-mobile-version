package com.example.android_6ringo.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
    Column(Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1.6f)) {
            Image(painterResource(id = R.drawable.spinnerbackground_1280_1), "",
                Modifier.fillMaxWidth(), contentScale = ContentScale.FillWidth)
            Box(
                Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = .5f)))
            Column(
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterStart)
                    .padding(horizontal = 16.dp)) {
                Text("Bienvenue sur 6ringo votre marché de rêve",
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(32.dp))
                Text("Choisissez votre catégorie",
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    HomeCategoryBox(iconRes = R.drawable.gold_icon, label = "Gold", color = GoldColor) {
                        navController.navigate("games/list?category=GOLD")
                    }

                    HomeCategoryBox(iconRes = R.drawable.platinum_icon, label = "Platinium", color = PlatiniumColor) {
                        navController.navigate("games/list?category=PLATINIUM")
                    }

                    HomeCategoryBox(iconRes = R.drawable.diamond_icon, label = "Diamond", color = DiamondColor) {
                        navController.navigate("games/list?category=DIAMOND")
                    }

                }
            }
        }
    }
}