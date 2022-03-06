package com.harrypotter.features.characters.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.harrypotter.R
import com.harrypotter.features.characters.vm.model.CharacterUI

class CharacterDetailPage {

    private val characterDetailMainViewGroup = onView(withId(R.id.characterDetailMainViewGroup))

    fun isPageDisplayed() {
        characterDetailMainViewGroup.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    fun checkExpectedDataIsShowed(character: CharacterUI) {
        // TODO Under dev
    }
}