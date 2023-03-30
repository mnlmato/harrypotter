package com.harrypotter.features.characters.data.repository

import com.harrypotter.coreapp.DataResult
import com.harrypotter.coreapp.exceptions.GenericException
import com.harrypotter.features.characters.main.data.datasource.api.CharactersApiDatasource
import com.harrypotter.features.characters.data.datasource.api.CharactersFakeApiGenerator
import com.harrypotter.features.characters.main.data.repository.CharactersRepositoryImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CharactersRepositoryImplTest : CharactersFakeApiGenerator {

    private val mockCharactersApiDataSource = mockk<CharactersApiDatasource>()
    private lateinit var subject: CharactersRepositoryImpl

    @Before
    fun setUp() {
        subject = CharactersRepositoryImpl(mockCharactersApiDataSource)
    }

    @Test
    fun `GIVEN a success response WHEN getCharacters THEN should returns a success result`() {
        coEvery { mockCharactersApiDataSource.getCharacters() } returns DataResult.Success(getCharactersResponseFake())

        val realResult = runBlocking { subject.getCharacters() }

        val expectedResult = DataResult.Success(getCharactersExpected())
        Assert.assertEquals(expectedResult::class, realResult::class)
    }

    @Test
    fun `GIVEN an error response WHEN getCharacters THEN should returns the same error result`() {
        coEvery { mockCharactersApiDataSource.getCharacters() } returns DataResult.Error(GenericException(""))

        val realResult = runBlocking { subject.getCharacters() }

        val expectedResult = DataResult.Error(GenericException(""))
        Assert.assertEquals(expectedResult, realResult)
    }
}