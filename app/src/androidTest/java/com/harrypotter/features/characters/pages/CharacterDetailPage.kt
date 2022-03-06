package com.harrypotter.features.characters.pages

import com.harrypotter.R
import com.harrypotter.features.ViewInteractionTest
import com.harrypotter.features.characters.vm.model.CharacterUI
import com.harrypotter.features.extensions.isDisplayed

class CharacterDetailPage {

    private val characterDetailMainViewGroup =
        ViewInteractionTest(R.id.characterDetailMainViewGroup)

    fun isPageDisplayed() {
        characterDetailMainViewGroup.isDisplayed()
    }

    fun checkExpectedDataIsShowed(character: CharacterUI) {
        // TODO Under dev
    }
}