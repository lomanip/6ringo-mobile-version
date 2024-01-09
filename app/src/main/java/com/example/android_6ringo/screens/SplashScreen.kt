package com.example.android_6ringo.screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android_6ringo.LocalComposeContext
import com.example.android_6ringo.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen() {
    val navController = LocalComposeContext.current.navController
    LaunchedEffect(Unit) {
        delay(2000)
        navController.navigate("home")
    }
    val infiniteTransition = rememberInfiniteTransition(label = "logo")
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = "logo_animate"
    )

    Column(Modifier.fillMaxSize(), 
        verticalArrangement = Arrangement.Center, 
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = R.drawable.six_ringo_logo), "",
            modifier = Modifier
                .size(256.dp)
                .rotate(angle))
        Spacer(Modifier.height(16.dp))
        
        
        Text(fontSize = 24.sp, fontWeight = FontWeight.SemiBold, text = buildAnnotatedString {
            withStyle(SpanStyle(color = MaterialTheme.colorScheme.secondary)) {
                append("6")
            }

            withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                append("ringo")
            }
        })
    }
}