package com.example.android_6ringo.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.android_6ringo.LocalComposeContext
import com.example.android_6ringo.auth.models.User
import com.example.android_6ringo.auth.services.AuthService
import org.kodein.di.instance


@Composable
fun AuthHomePage() {
    var user by remember { mutableStateOf(User()) }
    val container = LocalComposeContext.current.container
    val navController = LocalComposeContext.current.navController
    LaunchedEffect(Unit) {
        val authService: AuthService by container.kodein.instance()
        user = authService.user!!
    }

    val signOutState = rememberSignOutViewModel {
        navController.navigate("home")
    }

    SignOutDialog(signOutState)


    AuthHomeScaffold {
        LazyColumn() {
            item {
                Spacer(Modifier.height(8.dp))

                UserPersona(user)

                Divider(Modifier.padding(16.dp))

                Row(Modifier.padding(16.dp)) {
                    Button(onClick = { signOutState.open() }, modifier=Modifier.fillMaxWidth()) {
                        Icon(imageVector = Icons.Filled.Logout, contentDescription = "")
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(text = "Se déconnecter")
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthHomeScaffold(content: @Composable () -> Unit) {
    Scaffold(topBar = {
        TopAppBar(title = { Text("Mon compte") },
            navigationIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, "")
                }
            }
        )
    }) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            content()
        }
    }
}


@Composable
fun UserPersona(user: User) {
    Row(
        Modifier
            .fillMaxWidth()
            .clickable { }
            .padding(16.dp, 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(64.dp))
                .background(MaterialTheme.colorScheme.surface),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Filled.Person, "", modifier = Modifier.size(48.dp))
        }

        Column(
            Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = user.email, style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Icon(Icons.Filled.ChevronRight, "", modifier = Modifier.size(32.dp))
    }
}