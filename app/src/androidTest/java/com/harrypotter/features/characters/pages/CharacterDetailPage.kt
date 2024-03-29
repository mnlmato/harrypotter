package com.harrypotter.features.characters.pages

import androidx.compose.ui.test.junit4.ComposeTestRule
import com.harrypotter.features.characters.detail.ui.design.*
import com.harrypotter.viewinteraction.ViewInteractionTest

class CharacterDetailPage(composeTestRule: ComposeTestRule) {

    private val mainPageView =
        ViewInteractionTest(composeTestRule, testTag = CharacterDetailScreenTag.TOOLBAR.value)

    val nameDetailTextView =
        ViewInteractionTest(composeTestRule, testTag = CharacterDetailScreenTag.NAME.value)

    val houseDetailTextView =
        ViewInteractionTest(composeTestRule, testTag = CharacterDetailScreenTag.HOUSE.value)

    val actorNameDetailTextView =
        ViewInteractionTest(composeTestRule, testTag = CharacterDetailScreenTag.ACTOR.value)

    val genderDetailTextView =
        ViewInteractionTest(composeTestRule, testTag = CharacterDetailScreenTag.GENDER.value)

    val speciesDetailTextView =
        ViewInteractionTest(composeTestRule, testTag = CharacterDetailScreenTag.SPECIES.value)

    val birthdayDetailTextView =
        ViewInteractionTest(composeTestRule, testTag = CharacterDetailScreenTag.BIRTHDAY.value)

    fun isScreenDisplayed() {
        mainPageView.isDisplayed()
    }
}