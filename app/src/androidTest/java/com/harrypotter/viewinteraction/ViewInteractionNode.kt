package com.harrypotter.viewinteraction

import androidx.compose.ui.test.*

interface ViewInteractionNode {
    fun ViewInteractionTest.getViewInteraction(
        waitForNode: Boolean = false,
        viewInteraction: SemanticsNodeInteraction,
    ): SemanticsNodeInteraction {
        return if (waitForNode) {
            when {
                testTag != null -> waitForNodeWithTag(testTag)
                text != null -> waitForNodeWithText(text)
                else -> throw IllegalArgumentException("It is necessary to initialize the testTag or the text param to fetch the view")
            }
        } else {
            viewInteraction
        }
    }

    private fun ViewInteractionTest.waitForNodeWithTag(testTag: String): SemanticsNodeInteraction {
        composeTestRule.waitUntil {
            composeTestRule.onAllNodes(hasTestTag(testTag)).fetchSemanticsNodes().size == 1
        }
        return composeTestRule.onNodeWithTag(testTag)
    }

    private fun ViewInteractionTest.waitForNodeWithText(text: String): SemanticsNodeInteraction {
        composeTestRule.waitUntil {
            composeTestRule.onAllNodes(hasText(text)).fetchSemanticsNodes().size == 1
        }
        return composeTestRule.onNodeWithText(text)
    }
}