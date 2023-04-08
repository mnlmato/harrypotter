package com.harrypotter.viewinteraction

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.ComposeTestRule

class ViewInteractionTest(
    val composeTestRule: ComposeTestRule,
    val testTag: String? = null,
    val text: String? = null,
) : ViewInteractionNode {
    /**
     *  By default the viewInteraction it is created when the class is init
     * */
    private val viewInteraction: SemanticsNodeInteraction = when {
        testTag != null -> composeTestRule.onNodeWithTag(testTag)
        text != null -> composeTestRule.onNodeWithText(text)
        else -> throw IllegalArgumentException("It is necessary to initialize the testTag or the text param to fetch the view")
    }

    // asserts
    /**
     *  @param waitForView false then returns the default viewInteraction value.  If true, then it is necessary to wait for the node
     * */
    fun isDisplayed(waitForView: Boolean = false) {
        getViewInteraction(waitForView, viewInteraction).assertIsDisplayed()
    }

    fun isTextMatching(expectedResult: String, waitForView: Boolean = false) {
        getViewInteraction(waitForView, viewInteraction).assertTextEquals(expectedResult)
    }

    // perform actions
    fun performItemClickAtPosition(position: Int, waitForView: Boolean = false) {
        getViewInteraction(waitForView, viewInteraction).onChildAt(position).performClick()
    }
}