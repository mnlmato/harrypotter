package com.harrypotter.features.characters.commons.data.repository.mapper

import com.harrypotter.features.characters.main.domain.model.SpeciesType
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import org.junit.Test

class SpeciesValueResponseMapperTest {

    @Test
    fun `GIVEN a species value WHEN toType THEN should returns right species type`() {
        forAll(
            row(SPECIES_HUMAN, SpeciesType.HUMAN),
            row(SPECIES_HALF_GIANT, SpeciesType.HALF_GIANT),
            row(SPECIES_WEREWOLF, SpeciesType.WEREWOLF),
            row(SPECIES_CAT, SpeciesType.CAT),
            row(SPECIES_GOBLIN, SpeciesType.GOBLIN),
            row(SPECIES_OWL, SpeciesType.OWL),
            row(SPECIES_GHOST, SpeciesType.GHOST),
            row(SPECIES_POLTERGEIST, SpeciesType.POLTERGEIST),
            row(SPECIES_THREE_HEADED_DOG, SpeciesType.THREE_HEADED_DOG),
            row(SPECIES_DRAGON, SpeciesType.DRAGON),
            row(SPECIES_CENTAUR, SpeciesType.CENTAUR),
            row(SPECIES_HOUSE_ELF, SpeciesType.HOUSE_ELF),
            row(SPECIES_ACROMANTULA, SpeciesType.ACROMANTULA),
            row(SPECIES_HIPPOGRIFF, SpeciesType.HIPPOGRIFF),
            row(SPECIES_GIANT, SpeciesType.GIANT),
            row(SPECIES_VAMPIRE, SpeciesType.VAMPIRE),
            row(SPECIES_HALF_HUMAN, SpeciesType.HALF_HUMAN),
            row("", SpeciesType.UNKNOWN),
        ) { value, specieType ->
            val realResult = SpeciesValueResponseMapper.toType(value)

            val expectedResult: SpeciesType = specieType
            realResult shouldBe expectedResult
        }
    }
}

private const val SPECIES_HUMAN = "human"
private const val SPECIES_HALF_GIANT = "half-giant"
private const val SPECIES_WEREWOLF = "Werewolf"
private const val SPECIES_CAT = "cat"
private const val SPECIES_GOBLIN = "goblin"
private const val SPECIES_OWL = "owl"
private const val SPECIES_GHOST = "ghost"
private const val SPECIES_POLTERGEIST = "poltergeist"
private const val SPECIES_THREE_HEADED_DOG = "three-headed dog"
private const val SPECIES_DRAGON = "dragon"
private const val SPECIES_CENTAUR = "centaur"
private const val SPECIES_HOUSE_ELF = "house-elf"
private const val SPECIES_ACROMANTULA = "acromantula"
private const val SPECIES_HIPPOGRIFF = "hippogriff"
private const val SPECIES_GIANT = "giant"
private const val SPECIES_VAMPIRE = "vampire"
private const val SPECIES_HALF_HUMAN = "half-human"