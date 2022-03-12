package com.harrypotter.features.characters.vm.mapper

import com.harrypotter.features.characters.vm.CharactersFakeVMGenerator
import org.junit.Assert
import org.junit.Test

class CharactersMapperTest : CharactersFakeVMGenerator {

    @Test
    fun `GIVEN a characters with some attributes empty WHEN toCharactersUI THEN should returns a list of characters ui and generic value into empty attrs`() {
        val charactersFake = getCharactersFake()

        val realResult = charactersFake.toCharactersUI()

        val expectedResult = getCharactersUIExpected()
        Assert.assertEquals(expectedResult, realResult)
    }
}