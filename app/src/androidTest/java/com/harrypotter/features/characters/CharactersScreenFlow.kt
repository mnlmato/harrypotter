package com.harrypotter.features.characters

import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.harrypotter.coredata.di.UrlProviderModule
import com.harrypotter.features.characters.data.datasource.api.CHARACTERS_ENDPOINT
import com.harrypotter.features.characters.pages.CharacterDetailPage
import com.harrypotter.features.characters.pages.CharactersPage
import com.harrypotter.features.characters.ui.CharactersActivity
import com.harrypotter.features.characters.vm.model.CharacterUI
import com.harrypotter.rules.MockWebServerRule
import com.harrypotter.testdependencies.mockwebserver.getCharactersSuccessResponse
import com.harrypotter.testdependencies.mockwebserver.getError
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
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
        loadSuccessResponse()
        charactersPage.isPageDisplayed()
        charactersPage.loadingIsInvisible()
        charactersPage.clickItem(0)
        characterDetailPage.isPageDisplayed()
        characterDetailPage.checkExpectedDataIsShowed(
            CharacterUI(
                name = "Harry Potter",
                house = "Gryffindor",
                imageUrl = "http://hp-api.herokuapp.com/images/harry.jpg",
                actorName = "Daniel Radcliffe",
                gender = "male",
                species = "human",
                birth = "31-07-1980"
            )
        )
    }

    private fun loadSuccessResponse() {
        mockWebServerRule.mockWebServer.apply {
            dispatcher = object : Dispatcher() {
                override fun dispatch(request: RecordedRequest): MockResponse {
                    return when (request.path) {
                        "/$CHARACTERS_ENDPOINT" -> mockWebServerRule.mockWebServer.getCharactersSuccessResponse()
                        else -> mockWebServerRule.mockWebServer.getError()
                    }
                }
            }
        }
    }
}