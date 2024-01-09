package com.example.android_6ringo.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.ConfirmationNumber
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MonetizationOn
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun BottomBar(nav: NavController) {
    Surface(color = MaterialTheme.colorScheme.background) {
        Row(
            Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            BottomBarItem(onClick = { /*TODO*/ },
                selected = true,
                selectedContent = {
                    Icon(imageVector = Icons.Filled.Home, "")
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.Home,
                    contentDescription = ""
                )
            }

            BottomBarItem(onClick = {
                nav.navigate("spaces/list")
            }, selectedContent = {
                Icon(imageVector = Icons.Filled.MonetizationOn, "")
            }) {
                Icon(Icons.Outlined.MonetizationOn, "")
            }

            BottomBarItem(onClick = { /*TODO*/ },
                selectedContent = {
                    Icon(imageVector = Icons.Filled.ConfirmationNumber, "")
                }) {
                Icon(imageVector = Icons.Outlined.ConfirmationNumber, "")
            }

            BottomBarItem(onClick = { /*TODO*/ },
                selectedContent = {
                    Icon(Icons.Filled.Star, "")
                }) {
                Icon(Icons.Outlined.Star, "")
            }

            BottomBarItem(onClick = { /*TODO*/ },
                selectedContent = {
                    Icon(Icons.Filled.AccountCircle, "")
                }) {
                Icon(Icons.Outlined.AccountCircle, "")
            }
        }
    }
}


@Composable()
fun BottomBarItem(
    modifier: Modifier = Modifier, onClick: () -> Unit, selected: Boolean = false,
    selectedContent: @Composable () -> Unit,
    icon: @Composable () -> Unit,

    ) {

    val color =
        if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
    Surface(
        contentColor = color,
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .clickable(indication = rememberRipple(color = MaterialTheme.colorScheme.primary),
                interactionSource = remember { MutableInteractionSource() },
                onClick = { onClick() })
            .padding(16.dp)

    ) {
        if (selected) {
            selectedContent()
        } else {
            icon()
        }

    }
}