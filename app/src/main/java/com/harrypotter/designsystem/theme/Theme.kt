package com.harrypotter.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

private val LocalCustomColorScheme = staticCompositionLocalOf<ColorScheme> {
    error("No custom color scheme provided")
}

@Composable
fun CustomTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) CustomColorScheme.dark else CustomColorScheme.light

    CompositionLocalProvider(LocalCustomColorScheme provides colorScheme) {
        MaterialTheme(
            colorScheme = colorScheme,
            shapes = CustomShape.types,
            typography = CustomTypography.types,
            content = content,
        )
    }
}

object CustomThemeResources {
    val colors: ColorScheme
        @Composable
        get() = LocalCustomColorScheme.current
    val typography = CustomTypography.types
}