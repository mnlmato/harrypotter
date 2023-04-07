package com.harrypotter.coreui.errors

import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.layout.*
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
    onRetryButtonClickListener: OnRetryButtonClickListener,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize(),
    ) {
        Text(
            text = stringResource(id = R.string.generic_error_title),
            modifier = Modifier.testTag(GENERIC_ERROR_TEXT_TAG),
        )
        Spacer(modifier = Modifier.padding(all = 16.dp))
        Button(
            modifier = Modifier.testTag(GENERIC_ERROR_BUTTON_TAG),
            onClick = { onRetryButtonClickListener.onClick() }
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

@VisibleForTesting
const val GENERIC_ERROR_TEXT_TAG = "GENERIC_ERROR_TEXT_TAG"

@VisibleForTesting
const val GENERIC_ERROR_BUTTON_TAG = "GENERIC_ERROR_BUTTON_TAG"