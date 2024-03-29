package com.harrypotter.features.characters

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.harrypotter.coredata.di.UrlProviderModule
import com.harrypotter.features.characters.main.ui.CharactersActivity
import com.harrypotter.features.characters.main.vm.model.CharacterUI
import com.harrypotter.features.characters.pages.CharacterDetailPage
import com.harrypotter.features.characters.pages.CharactersPage
import com.harrypotter.rules.MockWebServerRule
import com.harrypotter.testdependencies.mockwebserver.CharactersMockResponseProvider
import com.jakewharton.espresso.OkHttp3IdlingResource
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import okhttp3.OkHttpClient
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@UninstallModules(UrlProviderModule::class)
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@LargeTest
class CharacterDetailScreenTest {

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

    @Before
    fun setup() {
        hiltRule.inject()
        IdlingRegistry.getInstance().register(OkHttp3IdlingResource.create("okhttp", okHttpClient))
    }

    @Test
    fun whenDetailScreenIsLoadedThenShowTheRightData() {
        val charactersSuccessMockResponse =
            CharactersMockResponseProvider.provideSuccessCharactersList()
        mockWebServerRule.mockWebServer.apply { enqueue(charactersSuccessMockResponse) }

        charactersPage.charactersList.performItemClickAtPosition(position = 0, waitForView = true)

        val expectedData = CharacterUI(
            id = "",
            name = "Harry Potter",
            house = "Gryffindor",
            imageUrl = "http://hp-api.herokuapp.com/images/harry.jpg",
            actorName = "Daniel Radcliffe",
            gender = "Male",
            species = "Human",
            birth = "31-07-1980"
        )
        with(expectedData) {
            characterDetailPage.nameDetailTextView.isTextMatching(name)
            characterDetailPage.houseDetailTextView.isTextMatching(house)
            characterDetailPage.actorNameDetailTextView.isTextMatching(actorName)
            characterDetailPage.genderDetailTextView.isTextMatching(gender)
            characterDetailPage.speciesDetailTextView.isTextMatching(species)
            characterDetailPage.birthdayDetailTextView.isTextMatching(birth)
        }
    }
}