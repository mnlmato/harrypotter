package com.harrypotter.features.characters

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.harrypotter.coredata.di.UrlProviderModule
import com.harrypotter.coreui.errors.GenericErrorPage
import com.harrypotter.features.characters.main.ui.CharactersActivity
import com.harrypotter.features.characters.pages.CharacterDetailPage
import com.harrypotter.features.characters.pages.CharactersPage
import com.harrypotter.rules.MockWebServerRule
import com.harrypotter.testdependencies.mockwebserver.CharactersMockResponseProvider
import com.harrypotter.testdependencies.mockwebserver.HttpCodeType
import com.jakewharton.espresso.OkHttp3IdlingResource
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import okhttp3.OkHttpClient
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

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val mockWebServerRule = MockWebServerRule()

    @get:Rule(order = 2)
    val composeTestRule = createAndroidComposeRule<CharactersActivity>()

    @Inject
    lateinit var okHttpClient: OkHttpClient

    private val charactersPage = CharactersPage(composeTestRule)
    private val characterDetailPage = CharacterDetailPage(composeTestRule)
    private val genericErrorPage = GenericErrorPage(composeTestRule)

    @Before
    fun setup() {
        hiltRule.inject()
        IdlingRegistry.getInstance().register(OkHttp3IdlingResource.create("okhttp", okHttpClient))
    }

    @After
    fun tearDown() {
        mockWebServerRule.mockWebServer.shutdown()
    }

    @Test
    fun givenSuccessResponseWhenClickOnItemCharactersDetailIsShowed() {
        val charactersSuccessMockResponse =
            CharactersMockResponseProvider.provideSuccessCharactersList()
        mockWebServerRule.mockWebServer.apply { enqueue(charactersSuccessMockResponse) }

        charactersPage.charactersList.performItemClickAtPosition(0, true)

        characterDetailPage.isScreenDisplayed()
    }

    @Test
    fun givenErrorScreenWhenClickRetryButtonAndNoErrorsThenShowSuccessScreen() {
        val errorMockResponse = CharactersMockResponseProvider.provideError(HttpCodeType.ERROR_404)
        mockWebServerRule.mockWebServer.apply { enqueue(errorMockResponse) }

        genericErrorPage.clickOnRetryButton()

        val charactersSuccessMockResponse =
            CharactersMockResponseProvider.provideSuccessCharactersList()
        mockWebServerRule.mockWebServer.apply { enqueue(charactersSuccessMockResponse) }

        charactersPage.charactersList.isDisplayed(true)
    }

    @Test
    fun givenErrorResponseWhenLoadScreenThenShowGenericErrorScreen() {
        val errorMockResponse = CharactersMockResponseProvider.provideError(HttpCodeType.ERROR_404)
        mockWebServerRule.mockWebServer.apply { enqueue(errorMockResponse) }

        genericErrorPage.isScreenDisplayed()
    }
}