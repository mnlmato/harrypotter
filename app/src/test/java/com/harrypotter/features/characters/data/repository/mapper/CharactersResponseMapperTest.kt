package com.harrypotter.features.characters.data.repository.mapper

import com.harrypotter.features.characters.data.datasource.api.CharactersFakeApiGenerator
import com.harrypotter.features.characters.main.domain.model.Characters
import com.harrypotter.features.characters.main.data.repository.mapper.toCharacters
import org.junit.Assert
import org.junit.Test

class CharactersResponseMapperTest : CharactersFakeApiGenerator {

    @Test
    fun `GIVEN a characters response list WHEN toCharacters THEN should returns a Characters object`() {
        val fakeResponse = getCharactersResponseFake()

        val realResult = fakeResponse.toCharacters()

        val expectedResult = Characters(getCharactersExpected())
        Assert.assertEquals(expectedResult, realResult)
    }
}