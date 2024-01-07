package com.example.android_6ringo.helpers

import androidx.compose.ui.graphics.Color
import com.example.android_6ringo.entities.Game
import com.example.android_6ringo.ui.theme.GreenDark
import com.example.android_6ringo.ui.theme.OrangeDark

fun String.formatGameStatus(): String {
    val normalized = this.uppercase()
    if(normalized == "ON_GOING") {
        return "Jackpot en cours"
    } else if(normalized == "UPCOMING") {
        return "Jackpot à venir"
    }
    return ""
}

fun Game.getStatusColor(): Color {
    val normalized = this.status.uppercase()
    if(normalized == "ON_GOING") {
        return OrangeDark
    } else if(normalized == "UPCOMING") {
        return GreenDark
    }
    return GreenDark
}