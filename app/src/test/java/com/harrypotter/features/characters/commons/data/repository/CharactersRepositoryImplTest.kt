package com.harrypotter.features.characters.commons.data.repository

import com.harrypotter.coreapp.DataResult
import com.harrypotter.coreapp.exceptions.GenericException
import com.harrypotter.features.characters.commons.data.datasource.api.CharactersApiDatasource
import com.harrypotter.features.characters.commons.data.datasource.api.CharactersFakeApiGenerator
import com.harrypotter.features.characters.commons.data.datasource.api.model.CharacterResponse
import com.harrypotter.features.characters.commons.data.datasource.cache.CharactersCacheDatasource
import com.harrypotter.features.characters.main.domain.model.Character
import com.harrypotter.features.characters.main.domain.model.GenderType
import com.harrypotter.features.characters.main.domain.model.HouseType
import com.harrypotter.features.characters.main.domain.model.SpeciesType
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CharactersRepositoryImplTest : CharactersFakeApiGenerator {

    private val mockCharactersApiDataSource = mockk<CharactersApiDatasource>()
    private val mockCharactersCacheDatasource = mockk<CharactersCacheDatasource>()
    private lateinit var subject: CharactersRepositoryImpl

    @Before
    fun setUp() {
        subject = CharactersRepositoryImpl(
            mockCharactersApiDataSource,
            mockCharactersCacheDatasource,
        )
    }

    @Test
    fun `GIVEN a success response WHEN getCharacters THEN should returns a success result`() {
        every { mockCharactersCacheDatasource.save(any()) } returns Unit
        coEvery { mockCharactersApiDataSource.getCharacters() } returns DataResult.Success(
            getCharactersResponseFake()
        )

        val realResult = runBlocking { subject.getCharacters() }

        val expectedResult = DataResult.Success(getCharactersExpected())
        Assert.assertEquals(expectedResult::class, realResult::class)
    }

    @Test
    fun `GIVEN a success response WHEN getCharacters THEN cache the result`() {
        every { mockCharactersCacheDatasource.save(getCharactersResponseFake()) } returns Unit
        coEvery { mockCharactersApiDataSource.getCharacters() } returns DataResult.Success(
            getCharactersResponseFake()
        )

        runBlocking { subject.getCharacters() }

        verify {
            mockCharactersCacheDatasource.save(getCharactersResponseFake())
        }
    }

    @Test
    fun `GIVEN an error response WHEN getCharacters THEN should returns the same error result`() {
        coEvery { mockCharactersApiDataSource.getCharacters() } returns DataResult.Error(
            GenericException("")
        )

        val realResult = runBlocking { subject.getCharacters() }

        val expectedResult = DataResult.Error(GenericException(""))
        Assert.assertEquals(expectedResult, realResult)
    }

    @Test
    fun `GIVEN a right character response from cache WHEN getCharacter THEN return the character`() {
        every { mockCharactersCacheDatasource.getCharacter(any()) } returns CharacterResponse(
            id = "FooId1",
            name = "FooName1",
            house = "Gryffindor",
            imageUrl = "FooImageUrl1",
            actorName = "FooActorName1",
            gender = "Male",
            species = "human",
            birth = "01-01-1986"
        )

        val realResult = subject.getCharacter("FooId1")

        val expectedResult = Character(
            id = "FooId1",
            name = "FooName1",
            house = HouseType.GRYFFINDOR,
            imageUrl = "FooImageUrl1",
            actorName = "FooActorName1",
            gender = GenderType.MALE,
            species = SpeciesType.HUMAN,
            birth = "01-01-1986"
        )
        Assert.assertEquals(expectedResult, realResult)
    }
}