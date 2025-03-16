package com.locationBasedApplication.ui.theme


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val OrangeColorScheme = lightColorScheme(
    primary = Orange40,
    secondary = Orange80,
    // You can set tertiary and other colors as needed
)

@Composable
fun OrangeLocationTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = OrangeColorScheme,
        typography = Typography,
        content = content
    )
}