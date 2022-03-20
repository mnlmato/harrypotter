package com.harrypotter.features.characters

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.IdlingRegistry
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
import com.harrypotter.testdependencies.mockwebserver.HttpCodeType
import com.harrypotter.testdependencies.mockwebserver.getCharactersSuccessResponse
import com.harrypotter.testdependencies.mockwebserver.getError
import com.harrypotter.viewinteraction.extensions.isDisplayed
import com.harrypotter.viewinteraction.extensions.isNotDisplayed
import com.harrypotter.viewinteraction.extensions.isTextMatching
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
    val activityScenarioRule = activityScenarioRule<CharactersActivity>()

    private val charactersPage = CharactersPage()
    private val characterDetailPage = CharacterDetailPage()

    @Inject
    lateinit var okHttpClient: OkHttpClient

    @Before
    fun setUp() {
        hiltRule.inject()

        // IdlingRegistry.getInstance().register(OkHttp3IdlingResource.create("okhttp", okHttpClient))
    }

    @After
    fun tearDown() {
        activityScenarioRule.scenario.close()
    }

    @Test
    fun givenSuccessResponseWhenClickOnItemCharactersDetailIsShowed() {
        mockWebServerRule.mockWebServer.apply { enqueue(getCharactersSuccessResponse()) }

        charactersPage.mainPageView.isDisplayed()

        charactersPage.clickItem(0)

        characterDetailPage.mainPageView.isDisplayed()
    }

    @Test
    fun whenDetailScreenIsLoadedThenShowTheRightData() {
        mockWebServerRule.mockWebServer.apply { enqueue(getCharactersSuccessResponse()) }

        charactersPage.clickItem(0)

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

    @Test
    fun giveSuccessResponseWhenScreenIsLoadingThenShowAndHideLoadingViews() {
        val loadingInvisibleIdlingResource = getLoadingInvisibleIdlingResource(activityScenarioRule)

        charactersPage.loadingView.isDisplayed()

        mockWebServerRule.mockWebServer.apply { enqueue(getCharactersSuccessResponse()) }

        IdlingRegistry.getInstance().register(loadingInvisibleIdlingResource)
        charactersPage.loadingView.isNotDisplayed()
        IdlingRegistry.getInstance().unregister(loadingInvisibleIdlingResource)
    }

    @Test
    fun givenErrorResponseWhenScreenIsLoadingThenGenericErrorIsDisplayed() {
        mockWebServerRule.mockWebServer.apply { enqueue(getError(HttpCodeType.ERROR_404)) }

        charactersPage.genericErrorMainView.isDisplayed()
    }
}