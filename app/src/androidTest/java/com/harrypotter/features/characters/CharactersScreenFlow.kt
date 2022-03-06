package com.harrypotter.features.characters

import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.harrypotter.features.characters.pages.CharacterDetailPage
import com.harrypotter.features.characters.pages.CharactersPage
import com.harrypotter.features.characters.ui.CharactersActivity
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class CharactersScreenFlow {

    @get:Rule
    val rule = activityScenarioRule<CharactersActivity>()

    private val charactersPage = CharactersPage()
    private val characterDetailPage = CharacterDetailPage()

    @After
    fun cleanUp() {
        rule.scenario.close()
    }

    @Test
    fun givenSuccessResponseWhenClickItemThenShowDetailScreen() {
        charactersPage.isPageDisplayed()
        charactersPage.loadingIsVisible()
        Thread.sleep(5000) // TODO Use until MockWebServer is implemented
        charactersPage.loadingIsInvisible()
        charactersPage.clickItem(0)
        characterDetailPage.isPageDisplayed()
    }

    // TODO Check an item contains the expected data in the holder
    // TODO Check the data in the detail is the expected
    // TODO Fetch the data from json, creates a random position and check the item of this position
}