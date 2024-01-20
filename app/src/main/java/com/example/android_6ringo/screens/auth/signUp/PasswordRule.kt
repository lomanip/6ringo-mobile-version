package com.example.android_6ringo.screens.auth.signUp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.android_6ringo.ui.theme.MsErrorBg
import com.example.android_6ringo.ui.theme.MsErrorContent
import com.example.android_6ringo.ui.theme.MsSuccessBg
import com.example.android_6ringo.ui.theme.MsSuccessContent


@Composable
fun PasswordRule(state: Boolean, text: String) {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        val bgColor = if(state) MsSuccessBg else MsErrorBg
        val contentColor = if (state) MsSuccessContent else MsErrorContent
        val icon = if (state) Icons.Filled.Check else Icons.Filled.Close

        val size = 12.dp
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(size)
                .clip(RoundedCornerShape(size))
                .background(bgColor)) {
            Icon(icon, contentDescription = "", tint = contentColor, modifier = Modifier.size(size-2.dp))
        }

        Spacer(Modifier.width(8.dp))

        Text(text, style=MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onBackground.copy(alpha=.6f))
    }
}