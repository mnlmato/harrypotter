package com.harrypotter.features.characters.vm.mapper

import com.harrypotter.coreui.stringconverter.StringConverter
import com.harrypotter.features.characters.vm.CharactersFakeVMGenerator
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test

class CharactersMapperTest : CharactersFakeVMGenerator {

    private val stringConverter = mockk<StringConverter>()

    @Test
    fun `GIVEN a characters with some attributes empty WHEN toCharactersUI THEN should returns a list of characters ui and generic value into empty attrs`() {
        every { stringConverter.convert(any()) } returns "Unknown"
        val charactersFake = getCharactersFake()

        val realResult = charactersFake.toCharactersUI(stringConverter)

        val expectedResult = getCharactersUIExpected()
        Assert.assertEquals(expectedResult, realResult)
    }
}