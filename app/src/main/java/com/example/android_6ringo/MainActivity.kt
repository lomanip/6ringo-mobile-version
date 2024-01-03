package com.example.android_6ringo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.android_6ringo.ui.theme.Android6ringoTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val snackbarInfo = remember { SnackbarHostState() }
            val snackbarError = remember { SnackbarHostState() }
            val keyboard = LocalSoftwareKeyboardController.current

            val composeContext =
                ComposeContext(navController, keyboard, snackbarInfo, snackbarError)

            CompositionLocalProvider(
                LocalComposeContext provides composeContext
            ) {

                Android6ringoTheme {
                    Surface(
                        color = MaterialTheme.colorScheme.background,
                        modifier = Modifier.fillMaxSize().imePadding()
                    ) {
                        Column(modifier = Modifier.fillMaxSize()) {
                            Box(modifier = Modifier.fillMaxWidth().weight(1f)) {
                                NavGraph()
                            }
                            SnackbarHost(
                                hostState = composeContext.snackbarInfoHostState,
                                modifier = Modifier.background(Color.Transparent)
                            ) { data ->
                                Snackbar(
                                    snackbarData = data,
                                    containerColor = MaterialTheme.colorScheme.surface,
                                    contentColor = MaterialTheme.colorScheme.onSurface,
                                    actionColor = MaterialTheme.colorScheme.primary
                                )
                            }

                            SnackbarHost(hostState = composeContext.snackbarErrorHostState) { data ->
                                Snackbar(
                                    snackbarData = data,
                                    containerColor = MaterialTheme.colorScheme.error,
                                    contentColor = MaterialTheme.colorScheme.onError,
                                    actionContentColor = MaterialTheme.colorScheme.onError,
                                    actionColor = MaterialTheme.colorScheme.error
                                )
                            }
                        }

                    }
                }
            }
        }
    }
}
