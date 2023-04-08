package com.harrypotter.coreui.errors

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick

class GenericErrorPage(composeTestRule: ComposeTestRule) {

    private val genericErrorTextView = composeTestRule.onNodeWithTag(GENERIC_ERROR_TEXT_TAG)
    private val genericErrorButton = composeTestRule.onNodeWithTag(GENERIC_ERROR_BUTTON_TAG)

    fun isScreenDisplayed() {
        genericErrorTextView.assertIsDisplayed()
    }

    fun clickOnRetryButton() {
        genericErrorButton.performClick()
    }
}