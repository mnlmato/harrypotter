package com.harrypotter.coreui.errors

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.harrypotter.R

@Composable
fun GenericErrorScreen(
    modifier: Modifier = Modifier,
    onRetryButtonClickListener: () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize(),
    ) {
        Text(
            text = stringResource(id = R.string.generic_error_title),
            modifier = Modifier.testTag(GenericErrorScreenTag.TEXT.value),
        )
        Spacer(modifier = Modifier.padding(all = 16.dp))
        Button(
            modifier = Modifier.testTag(GenericErrorScreenTag.BUTTON.value),
            onClick = { onRetryButtonClickListener() }
        ) {
            Text(text = stringResource(id = R.string.generic_error_retry_button))
        }
    }
}

@Composable
@Preview
fun GenericErrorScreenPreview() {
    GenericErrorScreen {}
}

enum class GenericErrorScreenTag(val value: String) {
    TEXT("GENERIC_ERROR_TEXT_TAG"),
    BUTTON("GENERIC_ERROR_BUTTON_TAG"),
}