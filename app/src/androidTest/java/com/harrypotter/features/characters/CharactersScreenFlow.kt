package com.harrypotter.features.characters

import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.harrypotter.coredata.di.UrlProviderModule
import com.harrypotter.features.characters.data.datasource.api.CHARACTERS_ENDPOINT
import com.harrypotter.features.characters.pages.CharacterDetailPage
import com.harrypotter.features.characters.pages.CharactersPage
import com.harrypotter.features.characters.ui.CharactersActivity
import com.harrypotter.testdependencies.PORT_LOCALHOST
import com.harrypotter.testdependencies.mockwebserver.getCharactersSuccessResponse
import com.harrypotter.testdependencies.mockwebserver.getError
import com.jakewharton.espresso.OkHttp3IdlingResource
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
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

    private val mockWebServer = MockWebServer()

    private val charactersPage = CharactersPage()
    private val characterDetailPage = CharacterDetailPage()

    @Inject
    lateinit var okHttp: OkHttpClient

    @Before
    fun setUp() {
        hiltRule.inject()
        mockWebServer.apply {
            dispatcher = object : Dispatcher() {
                override fun dispatch(request: RecordedRequest): MockResponse {
                    return when (request.path) {
                        "/$CHARACTERS_ENDPOINT" -> getCharactersSuccessResponse()
                        else -> getError()
                    }
                }
            }
            start(PORT_LOCALHOST)
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
}