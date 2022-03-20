package com.harrypotter.features.characters

import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.harrypotter.R
import com.harrypotter.coredata.di.UrlProviderModule
import com.harrypotter.features.characters.pages.CharacterDetailPage
import com.harrypotter.features.characters.pages.CharactersPage
import com.harrypotter.features.characters.ui.CharactersActivity
import com.harrypotter.features.characters.vm.model.CharacterUI
import com.harrypotter.rules.MockWebServerRule
import com.harrypotter.testdependencies.mockwebserver.getCharactersSuccessResponse
import com.harrypotter.viewinteraction.extensions.isDisplayed
import com.harrypotter.viewinteraction.extensions.isNotDisplayed
import com.harrypotter.viewinteraction.extensions.isTextMatching
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 *  https://medium.com/pulselive/espresso-testing-with-hilt-and-mockwebserver-82f7bcf5a525
 * */
@UninstallModules(UrlProviderModule::class)
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class CharactersScreenFlow {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val rule = activityScenarioRule<CharactersActivity>()

    @get:Rule(order = 2)
    val mockWebServerRule = MockWebServerRule()

    private val charactersPage = CharactersPage()
    private val characterDetailPage = CharacterDetailPage()

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @After
    fun tearDown() {
        rule.scenario.close()
    }

    @Test
    @LargeTest
    fun givenSuccessResponseWhenClickOnItemCharactersDetailIsShowed() {
        mockWebServerRule.mockWebServer.enqueue(mockWebServerRule.mockWebServer.getCharactersSuccessResponse())

        charactersPage.mainPageView.isDisplayed()
        charactersPage.loadingView.isNotDisplayed()

        charactersPage.clickItem(0)

        characterDetailPage.mainPageView.isDisplayed()
        val expectedData = CharacterUI(
            name = "Harry Potter",
            house = R.string.house_gryffindor,
            imageUrl = "http://hp-api.herokuapp.com/images/harry.jpg",
            actorName = "Daniel Radcliffe",
            gender = R.string.gender_male,
            species = R.string.species_human,
            birth = "31-07-1980"
        )
        with(expectedData) {
            characterDetailPage.nameDetailTextView.isTextMatching(name)
            characterDetailPage.houseDetailTextView.isTextMatching(house)
            characterDetailPage.actorNameDetailTextView.isTextMatching(actorName)
            characterDetailPage.genderDetailTextView.isTextMatching(gender)
            characterDetailPage.speciesDetailTextView.isTextMatching(species)
            characterDetailPage.birthDetailTextView.isTextMatching(birth)
        }
    }
}