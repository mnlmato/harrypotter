package com.harrypotter.features.characters.main.domain.usecase

import com.harrypotter.coreapp.DataResult
import com.harrypotter.coreapp.exceptions.GenericException
import com.harrypotter.features.characters.main.domain.CharactersRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetCharactersUseCaseTest : CharactersFakeDomainGenerator {

    private val mockCharactersRepository = mockk<CharactersRepository>()
    private lateinit var subject: GetCharactersUseCase

    @Before
    fun setup() {
        subject = GetCharactersUseCase(mockCharactersRepository)
    }

    @Test
    fun `GIVEN a success response with some data without name WHEN invoke THEN should returns a characters with valid data`() {
        coEvery { mockCharactersRepository.getCharacters() } returns DataResult.Success(
            getCharactersFake()
        )

        val realResult = runBlocking { subject() }

        val expectedResult = DataResult.Success(getCharactersExpected())
        Assert.assertEquals(expectedResult, realResult)
    }

    @Test
    fun `GIVEN an error response WHEN invoke THEN the result should returns the same error`() {
        coEvery { mockCharactersRepository.getCharacters() } returns DataResult.Error(
            GenericException("")
        )

        val realResult = runBlocking { mockCharactersRepository.getCharacters() }

        val expectedResult = DataResult.Error(GenericException(""))
        Assert.assertEquals(expectedResult, realResult)
    }
}