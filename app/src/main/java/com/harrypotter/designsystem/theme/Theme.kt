package com.harrypotter.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun CustomTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) CustomStyle.dark else CustomStyle.light

    MaterialTheme(
        colorScheme = colorScheme,
        shapes = CustomShapes.types,
        typography = CustomTypography.types,
        content = content,
    )
}