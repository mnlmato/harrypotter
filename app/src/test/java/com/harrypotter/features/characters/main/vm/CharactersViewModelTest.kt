package com.harrypotter.features.characters.main.vm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.harrypotter.coreapp.DataResult
import com.harrypotter.coreapp.exceptions.GenericException
import com.harrypotter.coreui.resourceprovider.ResourceProvider
import com.harrypotter.coreui.vm.ClickThrottler
import com.harrypotter.features.characters.main.domain.usecase.GetCharactersUseCase
import com.harrypotter.features.characters.main.vm.model.CharacterUI
import com.harrypotter.features.characters.main.vm.model.CharactersActionsFromUI
import com.harrypotter.features.characters.main.vm.model.CharactersState
import com.harrypotter.utils.CoroutinesDispatchersTestImpl
import com.harrypotter.utils.getOrAwaitValue
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.net.UnknownHostException

@ExperimentalCoroutinesApi
class CharactersViewModelTest : CharactersFakeVMGenerator {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testCoroutineDispatcher = UnconfinedTestDispatcher()
    private val getCharactersUseCase: GetCharactersUseCase = mockk()
    private val resourceProvider: ResourceProvider = mockk()
    private val clickThrottler: ClickThrottler = mockk()
    private val coroutinesDispatchers = CoroutinesDispatchersTestImpl(testCoroutineDispatcher)
    private lateinit var subject: CharactersViewModel

    @Before
    fun setup() {
        subject = CharactersViewModel(
            getCharactersUseCase,
            resourceProvider,
            clickThrottler,
            coroutinesDispatchers,
        )
    }

    @Test
    fun `GIVEN result success WHEN loadCharacters THEN livedata should show right charactersUI`() {
        coEvery { getCharactersUseCase() } returns DataResult.Success(getCharactersFake())
        every { resourceProvider.getString(any()) } returns "Foo"

        subject.loadCharacters()

        val realResult = subject.charactersStateEvent.getOrAwaitValue()
        val expectedResult = CharactersState.UI.Success(getCharactersUIExpected())
        realResult shouldBe expectedResult
    }

    @Test
    fun `GIVEN result error WHEN loadCharacters THEN livedata should show generic error`() {
        coEvery { getCharactersUseCase() } returns DataResult.Error(GenericException(""))

        subject.loadCharacters()

        val realResult = subject.charactersStateEvent.getOrAwaitValue()
        val expectedResult = CharactersState.UI.Error
        realResult shouldBe expectedResult
    }

    @Test
    fun `WHEN onItemClick THEN show detail screen event passing the id`() {
        coEvery { clickThrottler.onClick(any()) } answers {
            runBlocking { firstArg<suspend () -> Unit>().invoke() }
        }

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
        subject.onCLickAction(CharactersActionsFromUI.ItemListClick(characterUI))

        val realResult = subject.actionsSingleEvents.getOrAwaitValue()
        realResult shouldBe CharactersState.SingleActions.ShowCharacterDetail("FooId1")
    }

    @Test
    fun `WHEN onRetryButtonClick THEN load data again`() {
        coEvery { getCharactersUseCase() } returns DataResult.Error(UnknownHostException())

        subject.onCLickAction(CharactersActionsFromUI.RetryButtonClick)

        coVerify {
            getCharactersUseCase()
        }
    }

    @Test
    fun `GIVEN a result WHEN loadCharacters THEN show loading before response`() {
        coEvery { getCharactersUseCase() } returns DataResult.Success(getCharactersFake())

        subject.loadCharacters()

        val isLoadingShowedBeforeResponseExpected = CharactersState.UI.Loading
        val isLoadingShowedBeforeResponseReal = subject.charactersStateEvent.getOrAwaitValue()
        isLoadingShowedBeforeResponseReal shouldBe isLoadingShowedBeforeResponseExpected
    }
}