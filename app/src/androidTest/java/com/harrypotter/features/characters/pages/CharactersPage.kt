package com.harrypotter.features.characters.pages

import androidx.compose.ui.test.junit4.ComposeTestRule
import com.harrypotter.features.characters.main.ui.design.CharactersListScreenTag
import com.harrypotter.viewinteraction.ViewInteractionTest

class CharactersPage(composeTestRule: ComposeTestRule) {

    val charactersList =
        ViewInteractionTest(composeTestRule, testTag = CharactersListScreenTag.LIST.value)
}