package com.harrypotter.features.characters.pages

import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.harrypotter.R
import com.harrypotter.features.characters.vm.model.CharacterUI
import com.harrypotter.viewinteraction.ViewInteractionTest
import com.harrypotter.viewinteraction.extensions.isDisplayed

class CharacterDetailPage {

    private val mainViewGroup = ViewInteractionTest(R.id.characterDetailMainViewGroup)
    private val nameDetailTextView = ViewInteractionTest(R.id.nameDetailTextView)
    private val houseDetailTextView = ViewInteractionTest(R.id.houseDetailTextView)
    private val actorNameDetailTextView = ViewInteractionTest(R.id.actorNameDetailTextView)
    private val genderDetailTextView = ViewInteractionTest(R.id.genderDetailTextView)
    private val speciesDetailTextView = ViewInteractionTest(R.id.speciesDetailTextView)
    private val birthDetailTextView = ViewInteractionTest(R.id.birthDetailTextView)

    fun isPageDisplayed() {
        mainViewGroup.isDisplayed()
    }

    fun checkExpectedDataIsShowed(expectedData: CharacterUI) {
        with(expectedData) {
            nameDetailTextView.viewInteraction.check(matches(withText(name)))
            houseDetailTextView.viewInteraction.check(matches(withText(house)))
            actorNameDetailTextView.viewInteraction.check(matches(withText(actorName)))
            genderDetailTextView.viewInteraction.check(matches(withText(gender)))
            speciesDetailTextView.viewInteraction.check(matches(withText(species)))
            birthDetailTextView.viewInteraction.check(matches(withText(birth)))
        }
    }
}