package com.harrypotter.features.characters.pages

import androidx.compose.ui.test.junit4.ComposeTestRule
import com.harrypotter.features.characters.detail.ui.design.*
import com.harrypotter.viewinteraction.ViewInteractionTest

class CharacterDetailPage(composeTestRule: ComposeTestRule) {

    private val mainPageView =
        ViewInteractionTest(composeTestRule, testTag = CHARACTER_DETAIL_SCREEN_TOOLBAR_TEST_TAG)

    val nameDetailTextView =
        ViewInteractionTest(composeTestRule, testTag = CHARACTER_DETAIL_SCREEN_NAME_TEST_TAG)

    val houseDetailTextView =
        ViewInteractionTest(composeTestRule, testTag = CHARACTER_DETAIL_SCREEN_HOUSE_TEST_TAG)

    val actorNameDetailTextView =
        ViewInteractionTest(composeTestRule, testTag = CHARACTER_DETAIL_SCREEN_ACTOR_NAME_TEST_TAG)

    val genderDetailTextView =
        ViewInteractionTest(composeTestRule, testTag = CHARACTER_DETAIL_SCREEN_GENDER_TEST_TAG)

    val speciesDetailTextView =
        ViewInteractionTest(composeTestRule, testTag = CHARACTER_DETAIL_SCREEN_SPECIES_TEST_TAG)

    val birthdayDetailTextView =
        ViewInteractionTest(composeTestRule, testTag = CHARACTER_DETAIL_SCREEN_BIRTHDAY_TEST_TAG)

    fun isScreenDisplayed() {
        mainPageView.isDisplayed()
    }
}