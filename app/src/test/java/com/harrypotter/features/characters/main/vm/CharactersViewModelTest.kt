package com.harrypotter.features.characters.main.vm

import com.harrypotter.coreapp.DataResult
import com.harrypotter.coreapp.exceptions.GenericException
import com.harrypotter.coreui.resourceprovider.ResourceProvider
import com.harrypotter.features.characters.main.domain.usecase.GetCharactersUseCase
import com.harrypotter.features.characters.main.vm.model.CharacterUI
import com.harrypotter.features.characters.main.vm.model.CharactersState
import com.harrypotter.utils.ClickThrottlerTest
import com.harrypotter.utils.CoroutinesDispatchersTestImpl
import com.harrypotter.utils.collectLastValue
import com.harrypotter.utils.collectTest
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class CharactersViewModelTest : CharactersFakeVMGenerator {

    private val testCoroutineDispatcher = UnconfinedTestDispatcher()
    private val getCharactersUseCase: GetCharactersUseCase = mockk()
    private val clickThrottler: ClickThrottlerTest = ClickThrottlerTest()
    private val resourceProvider: ResourceProvider = mockk()
    private val coroutinesDispatchers = CoroutinesDispatchersTestImpl(testCoroutineDispatcher)
    private lateinit var subject: CharactersViewModel

    @Before
    fun setUp() {
        subject = CharactersViewModel(
            getCharactersUseCase,
            clickThrottler,
            resourceProvider,
            coroutinesDispatchers,
        )
    }

    @Test
    fun `GIVEN result success WHEN loadCharacters THEN should show right charactersUI`() = runTest {
        coEvery { getCharactersUseCase() } returns DataResult.Success(getCharactersFake())
        every { resourceProvider.getString(any()) } returns "Foo"

        Assert.assertTrue(subject.charactersEvent.collectLastValue() is CharactersState.UI.Loading)

        subject.loadCharacters()

        Assert.assertTrue(subject.charactersEvent.collectLastValue() is CharactersState.UI.Success)
    }

    @Test
    fun `GIVEN result error WHEN loadCharacters THEN should show generic error`() = runTest {
        coEvery { getCharactersUseCase() } returns DataResult.Error(GenericException(""))

        subject.loadCharacters()

        val realResult = subject.charactersEvent.collectLastValue()
        val expectedResult = CharactersState.UI.Error
        Assert.assertEquals(expectedResult, realResult)
    }

    @Test
    fun `WHEN onItemClick THEN show detail screen event passing the id`() =
        runTest(testCoroutineDispatcher) {
            val characterUI = CharacterUI(
                id = "FooId1",
                name = "FooName1",
                house = "Foo",
                imageUrl = "FooImageUrl1",
                actorName = "FooActorName1",
                gender = "Foo",
                species = "Foo",
                birth = "01-01-1986"
            )


            val eventsList = mutableListOf<CharactersState>()
            subject.charactersEvent.collectTest(this, list = eventsList)

            subject.onItemClick(characterUI)

            val realResult = eventsList.last()
            val expectedResult = CharactersState.Actions.NavigateDetail("FooId1")
            Assert.assertEquals(expectedResult, realResult)
        }
}