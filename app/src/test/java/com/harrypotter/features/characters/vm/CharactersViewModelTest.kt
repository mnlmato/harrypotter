package com.harrypotter.features.characters.vm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.harrypotter.coreapp.DataResult
import com.harrypotter.coreapp.exceptions.GenericException
import com.harrypotter.coreui.stringconverter.StringConverter
import com.harrypotter.features.characters.domain.usecase.GetCharactersUseCase
import com.harrypotter.utils.CoroutinesDispatchersTestImpl
import com.harrypotter.utils.getOrAwaitValue
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.*

@ExperimentalCoroutinesApi
class CharactersViewModelTest : CharactersFakeVMGenerator {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val getCharactersUseCase = mockk<GetCharactersUseCase>()
    private val stringConverter = mockk<StringConverter>()
    private val coroutinesDispatchers = CoroutinesDispatchersTestImpl(testCoroutineDispatcher)
    private lateinit var subject: CharactersViewModel

    @Before
    fun setUp() {
        subject = CharactersViewModel(
            getCharactersUseCase,
            stringConverter,
            coroutinesDispatchers
        )
    }

    @After
    fun tearDown() {
        testCoroutineDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `GIVEN result success WHE loadCharacters THEN livedata should show right charactersUI`() {
        coEvery { getCharactersUseCase() } returns DataResult.Success(getCharactersFake())
        every { stringConverter.convert(any()) } returns "Unknown"

        subject.loadCharacters()

        val realResult = subject.charactersEvent.getOrAwaitValue()
        val expectedResult = getCharactersUIExpected()
        Assert.assertEquals(expectedResult, realResult)
    }

    @Test
    fun `GIVEN result error WHEN loadCharacters THEN livedata should show generic error`() {
        coEvery { getCharactersUseCase() } returns DataResult.Error(GenericException(""))

        subject.loadCharacters()

        val realResult = subject.showGenericErrorEvent.getOrAwaitValue()
        val expectedResult = true
        Assert.assertEquals(expectedResult, realResult)
    }

    @Test
    fun `WHEN onItemClick THEN show detail screen event`() {
        val expectedResult = getCharactersUIExpected().first()

        subject.onItemClick(expectedResult)

        val realResult = subject.showDetailEvent.getOrAwaitValue()
        Assert.assertEquals(expectedResult, realResult)
    }

    @Test
    fun `GIVEN a result WHEN loadCharacters THEN show loading before response and hide loading after response`() {
        coEvery { getCharactersUseCase() } returns DataResult.Success(getCharactersFake())

        testCoroutineDispatcher.pauseDispatcher()
        subject.loadCharacters()

        val isLoadingShowedBeforeResponseExpected = true
        val isLoadingShowedBeforeResponseReal = subject.showLoadingEvent.getOrAwaitValue()
        Assert.assertEquals(
            isLoadingShowedBeforeResponseExpected,
            isLoadingShowedBeforeResponseReal
        )

        testCoroutineDispatcher.resumeDispatcher()
        val isLoadingShowedAfterResponseExpected = true
        val isLoadingShowedAfterResponseReal = subject.showLoadingEvent.getOrAwaitValue()
        Assert.assertEquals(
            isLoadingShowedAfterResponseExpected,
            isLoadingShowedAfterResponseReal
        )
    }
}