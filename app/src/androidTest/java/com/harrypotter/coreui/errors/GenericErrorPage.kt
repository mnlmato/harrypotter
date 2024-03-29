package com.harrypotter.coreui.errors

import androidx.compose.ui.test.junit4.ComposeTestRule
import com.harrypotter.viewinteraction.ViewInteractionTest

class GenericErrorPage(composeTestRule: ComposeTestRule) {

    private val genericErrorTextView = ViewInteractionTest(composeTestRule, GenericErrorScreenTag.TEXT.value)
    private val genericErrorButton = ViewInteractionTest(composeTestRule, GenericErrorScreenTag.BUTTON.value)

    fun isScreenDisplayed() {
        genericErrorTextView.isDisplayed(waitForView = true)
    }

    fun clickOnRetryButton() {
        genericErrorButton.performClick(waitForView = true)
    }
}