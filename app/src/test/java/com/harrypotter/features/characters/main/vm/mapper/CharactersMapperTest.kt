package com.harrypotter.features.characters.main.vm.mapper

import com.harrypotter.coreui.resourceprovider.ResourceProvider
import com.harrypotter.features.characters.main.vm.CharactersFakeVMGenerator
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.junit.Test

class CharactersMapperTest : CharactersFakeVMGenerator {

    private val resourceProvider: ResourceProvider = mockk()

    @Test
    fun `GIVEN a characters with some attributes empty WHEN toCharactersUI THEN should returns a list of characters ui and generic value into empty attrs`() {
        val charactersFake = getCharactersFake()
        every { resourceProvider.getString(any()) } returns "Foo"

        val realResult = charactersFake.toCharactersUI(resourceProvider)

        val expectedResult = getCharactersUIExpected()
        realResult shouldBe expectedResult
    }
}