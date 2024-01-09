package com.example.android_6ringo.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.android_6ringo.R
import com.example.android_6ringo.ui.theme.OrangeDark

@Composable
fun HomeCategoryBox(iconRes: Int, label: String, color: Color, onClick: ()-> Unit) {
    Box(
        Modifier
            .width(110.dp)
            .clip(RoundedCornerShape(4.dp))
            .aspectRatio(1.61f)
            .clickable { onClick() }
            .background(Color.White)) {
        Column(Modifier.align(Alignment.Center).background(color.copy(alpha = .3f)).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
            ) {
            Image(painterResource(id = iconRes), "", modifier = Modifier.size(32.dp))
            Spacer(Modifier.height(4.dp))
            Text(
                label,
                color = color,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold
            )

        }
    }
}