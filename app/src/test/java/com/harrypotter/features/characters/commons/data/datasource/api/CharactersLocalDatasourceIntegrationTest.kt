package com.harrypotter.features.characters.commons.data.datasource.api

import com.google.gson.Gson
import com.harrypotter.coredata.JsonReader
import com.harrypotter.features.characters.commons.data.datasource.api.model.CharacterResponse
import com.harrypotter.features.characters.commons.data.datasource.local.CharactersLocalDatasource
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CharactersLocalDatasourceIntegrationTest {

    /**
     *  Real Gson instance to test the expected result is matching with the fake deserialized json
     * */
    private val gson = Gson()
    private val jsonReader = mockk<JsonReader>()
    private lateinit var subject: CharactersLocalDatasource

    private val fakeCharactersStringFromJson =
        "[{\"id\":\"9e3f7ce4-b9a7-4244-b709-dae5c1f1d4a8\",\"name\":\"Harry Potter\",\"species\":\"human\",\"gender\":\"male\",\"house\":\"Gryffindor\",\"dateOfBirth\":\"31-07-1980\",\"actor\":\"Daniel Radcliffe\",\"image\":\"https://ik.imagekit.io/hpapi/harry.jpg\"}]"

    @Before
    fun setup() {
        subject = CharactersLocalDatasource(gson, jsonReader)
    }

    @Test
    fun `GIVEN a success json WHEN getCharacters THEN should returns a non empty list of CharacterResponse`() {
        every { jsonReader.read(any()) } returns fakeCharactersStringFromJson
        val characterResponse = CharacterResponse(
            id = "9e3f7ce4-b9a7-4244-b709-dae5c1f1d4a8",
            name = "Harry Potter",
            species = "human",
            gender = "male",
            house = "Gryffindor",
            birth = "31-07-1980",
            actorName = "Daniel Radcliffe",
            imageUrl = "https://ik.imagekit.io/hpapi/harry.jpg"
        )

        val realResult = subject.getCharacters()

        val expectedResult = listOf(characterResponse)
        Assert.assertEquals(expectedResult, realResult)
    }

    @Test
    fun `GIVEN a no valid json during deserialization WHEN getCharacters THEN should returns an empty list of CharacterResponse`() {
        every { jsonReader.read(any()) } returns "noValidJson"

        val realResult = subject.getCharacters()

        Assert.assertTrue(realResult.isEmpty())
    }
}