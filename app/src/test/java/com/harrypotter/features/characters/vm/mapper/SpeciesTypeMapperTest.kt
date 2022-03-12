package com.harrypotter.features.characters.vm.mapper

import com.harrypotter.R
import com.harrypotter.features.characters.domain.model.SpeciesType
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import org.junit.Assert
import org.junit.Test

class SpeciesTypeMapperTest {

    @Test
    fun `GIVEN a speciesType WHEN toStringRes THEN should returns right string res`() {
        forAll(
            row(SpeciesType.HUMAN, R.string.species_human),
            row(SpeciesType.HALF_GIANT, R.string.species_half_giant),
            row(SpeciesType.WEREWOLF, R.string.species_werewolf),
            row(SpeciesType.CAT, R.string.species_cat),
            row(SpeciesType.GOBLIN, R.string.species_goblin),
            row(SpeciesType.OWL, R.string.species_owl),
            row(SpeciesType.GHOST, R.string.species_ghost),
            row(SpeciesType.POLTERGEIST, R.string.species_poltergeist),
            row(SpeciesType.THREE_HEADED_DOG, R.string.species_three_headed_dog),
            row(SpeciesType.DRAGON, R.string.species_dragon),
            row(SpeciesType.CENTAUR, R.string.species_centaur),
            row(SpeciesType.HOUSE_ELF, R.string.species_house_elf),
            row(SpeciesType.ACROMANTULA, R.string.species_acromantula),
            row(SpeciesType.HIPPOGRIFF, R.string.species_hippogriff),
            row(SpeciesType.GIANT, R.string.species_giant),
            row(SpeciesType.VAMPIRE, R.string.species_vampire),
            row(SpeciesType.HALF_HUMAN, R.string.species_half_human),
            row(SpeciesType.UNKNOWN, R.string.species_unkwon),
        ) { specieType, stringRes ->
            val realResult = specieType.toStringRes()

            val expectedResult: Int = stringRes
            Assert.assertEquals(expectedResult, realResult)
        }
    }
}