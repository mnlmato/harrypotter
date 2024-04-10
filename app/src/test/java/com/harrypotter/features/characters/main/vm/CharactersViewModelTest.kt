package com.harrypotter.features.characters.main.vm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.harrypotter.coreapp.DataResult
import com.harrypotter.coreapp.exceptions.GenericException
import com.harrypotter.coreui.resourceprovider.ResourceProvider
import com.harrypotter.features.characters.main.domain.usecase.GetCharactersUseCase
import com.harrypotter.features.characters.main.vm.model.CharacterUI
import com.harrypotter.features.characters.main.vm.model.CharactersState
import com.harrypotter.utils.CoroutinesDispatchersTestImpl
import com.harrypotter.utils.getOrAwaitValue
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class CharactersViewModelTest : CharactersFakeVMGenerator {

    private val testCoroutineDispatcher = UnconfinedTestDispatcher()
    private val getCharactersUseCase: GetCharactersUseCase = mockk()
    private val resourceProvider: ResourceProvider = mockk()
    private val coroutinesDispatchers = CoroutinesDispatchersTestImpl(testCoroutineDispatcher)
    private lateinit var subject: CharactersViewModel

    @BeforeEach
    fun setUp() {
        subject = CharactersViewModel(
            getCharactersUseCase,
            resourceProvider,
            coroutinesDispatchers
        )
    }

    @Test
    fun `GIVEN result success WHEN loadCharacters THEN livedata should show right charactersUI`() {
        coEvery { getCharactersUseCase() } returns DataResult.Success(getCharactersFake())
        every { resourceProvider.getString(any()) } returns "Foo"

        subject.loadCharacters()

        val realResult = subject.charactersStateEvent.getOrAwaitValue()
        val expectedResult = CharactersState.Success(getCharactersUIExpected())
        Assertions.assertEquals(expectedResult, realResult)
    }

    @Test
    fun `GIVEN result error WHEN loadCharacters THEN livedata should show generic error`() {
        coEvery { getCharactersUseCase() } returns DataResult.Error(GenericException(""))

        subject.loadCharacters()

        val realResult = subject.charactersStateEvent.getOrAwaitValue()
        val expectedResult = CharactersState.Error
        Assertions.assertEquals(expectedResult, realResult)
    }

    @Test
    fun `WHEN onItemClick THEN show detail screen event passing the id`() {
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
        subject.onItemClick(characterUI)

        val realResult = subject.showDetailEvent.getOrAwaitValue()
        Assertions.assertEquals("FooId1", realResult)
    }

    @Test
    fun `GIVEN a result WHEN loadCharacters THEN show loading before response`() {
        coEvery { getCharactersUseCase() } returns DataResult.Success(getCharactersFake())

        subject.loadCharacters()

        val isLoadingShowedBeforeResponseExpected = CharactersState.Loading
        val isLoadingShowedBeforeResponseReal = subject.charactersStateEvent.getOrAwaitValue()
        Assertions.assertEquals(
            isLoadingShowedBeforeResponseExpected,
            isLoadingShowedBeforeResponseReal
        )
    }
}