package com.harrypotter.designsystem.components.loading

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LoadingCustom(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier,
    ) {
        CircularProgressIndicator()
    }
}

@Composable
@Preview
fun LoadingCustomPreview() {
    LoadingCustom(modifier = Modifier.fillMaxSize())
}