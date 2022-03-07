package com.harrypotter.features.characters

import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.harrypotter.coredata.UrlProviderModule
import com.harrypotter.features.characters.pages.CharacterDetailPage
import com.harrypotter.features.characters.pages.CharactersPage
import com.harrypotter.features.characters.ui.CharactersActivity
import com.harrypotter.testdependencies.MockWebServerDispatcher
import com.jakewharton.espresso.OkHttp3IdlingResource
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

/**
 *  https://medium.com/pulselive/espresso-testing-with-hilt-and-mockwebserver-82f7bcf5a525
 * */
@UninstallModules(UrlProviderModule::class)
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@LargeTest
class CharactersScreenFlow {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val rule = activityScenarioRule<CharactersActivity>()

    lateinit var mockWebServer: MockWebServer

    private val charactersPage = CharactersPage()
    private val characterDetailPage = CharacterDetailPage()

    @Inject
    lateinit var okHttp: OkHttpClient

    @Before
    fun setUp() {
        hiltRule.inject()
        mockWebServer = MockWebServer().apply {
            dispatcher = MockWebServerDispatcher().RequestDispatcher()
            start(8080)
        }
        IdlingRegistry.getInstance().register(OkHttp3IdlingResource.create("okhttp", okHttp))
    }

    @After
    fun tearDown() {
        rule.scenario.close()
        mockWebServer.shutdown()
    }

    @Test
    fun givenSuccessResponseWhenClickItemThenShowDetailScreen() {
        charactersPage.isPageDisplayed()
        // charactersPage.loadingIsVisible() TODO Research how to test this first state
        charactersPage.loadingIsInvisible()
        charactersPage.clickItem(0)
        characterDetailPage.isPageDisplayed()
    }

    // TODO Check an item contains the expected data in the holder
    // TODO Check the data in the detail is the expected
    // TODO Fetch the data from json, creates a random position and check the item of this position
}