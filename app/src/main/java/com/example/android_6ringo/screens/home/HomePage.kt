package com.example.android_6ringo.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.android_6ringo.R

@Composable
fun HomePage() {
    Column(Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(painterResource(id = R.drawable.spinnerbackground_1280_1), "", Modifier.fillMaxWidth())
            Box(Modifier.fillMaxWidth().align(Alignment.CenterStart)) {
                Text("Bienvenue sur 6ringo Votre marché de rêve",
                    style = MaterialTheme.typography.titleLarge)
            }
        }
    }
}