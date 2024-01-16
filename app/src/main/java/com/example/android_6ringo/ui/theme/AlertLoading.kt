package com.example.android_6ringo.ui.theme

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog


@Composable
fun AlertLoading(state: MutableState<Boolean>, content: String) {
    AlertLoading(state) {
        Text(text = content, style = MaterialTheme.typography.bodyMedium)
    }
}
@Composable
fun AlertLoading(state: MutableState<Boolean>, content: @Composable () -> Unit) {

    if(state.value) {
        Dialog(onDismissRequest = { /*TODO*/ }) {
            Surface(color = MaterialTheme.colorScheme.surface) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(24.dp), verticalAlignment = Alignment.CenterVertically
                ) {
                    CircularProgressIndicator(Modifier.width(48.dp))
                    Spacer(modifier = Modifier.width(24.dp))
                    content()
                }
            }
        }
    }
}