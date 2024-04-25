package com.harrypotter.features.characters.commons.data.datasource.api

import com.harrypotter.coreapp.DataResult
import com.harrypotter.coreapp.exceptions.NetworkException
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.io.IOException

class CharactersApiDatasourceTest : CharactersFakeApiGenerator {

    private val mockCharactersApi = mockk<CharactersApi>()
    private lateinit var subject: CharactersApiDatasource

    @Before
    fun setUp() {
        subject = CharactersApiDatasource(mockCharactersApi)
    }

    @Test
    fun `GIVEN a list result WHEN getCharacters THEN the result should be success`() {
        coEvery { mockCharactersApi.getCharacters() } returns getCharactersResponseFake()

        val realResult = runBlocking { subject.getCharacters() }

        val expectedResult = DataResult.Success(getCharactersResponseFake())
        realResult shouldBe expectedResult
    }

    @Test
    fun `GIVEN an exception WHEN getCharacters THEN the result should be an error`() {
        coEvery { mockCharactersApi.getCharacters() } throws IOException()

        val realResult = runBlocking { subject.getCharacters() }

        val expectedResult = DataResult.Error(NetworkException(""))
        realResult shouldBe expectedResult
    }
}