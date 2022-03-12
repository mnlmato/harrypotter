package com.harrypotter.features.characters.data.repository.mapper

import com.harrypotter.coredata.mapper.ValueResponseMapper
import com.harrypotter.features.characters.domain.model.SpeciesType

@JvmInline
value class SpecieResponseMapper(override val value: String) : ValueResponseMapper<SpeciesType> {
    override fun toType() = when (value.lowercase()) {
        SPECIES_HUMAN -> SpeciesType.HUMAN
        SPECIES_HALF_GIANT -> SpeciesType.HALF_GIANT
        SPECIES_WEREWOLF -> SpeciesType.WEREWOLF
        SPECIES_CAT -> SpeciesType.CAT
        SPECIES_GOBLIN -> SpeciesType.GOBLIN
        SPECIES_OWL -> SpeciesType.OWL
        SPECIES_GHOST -> SpeciesType.GHOST
        SPECIES_POLTERGEIST -> SpeciesType.POLTERGEIST
        SPECIES_THREE_HEADED_DOG -> SpeciesType.THREE_HEADED_DOG
        SPECIES_DRAGON -> SpeciesType.DRAGON
        SPECIES_CENTAUR -> SpeciesType.CENTAUR
        SPECIES_HOUSE_ELF -> SpeciesType.HOUSE_ELF
        SPECIES_ACROMANTULA -> SpeciesType.ACROMANTULA
        SPECIES_HIPPOGRIFF -> SpeciesType.HIPPOGRIFF
        SPECIES_GIANT -> SpeciesType.GIANT
        SPECIES_VAMPIRE -> SpeciesType.VAMPIRE
        SPECIES_HALF_HUMAN -> SpeciesType.HALF_HUMAN
        else -> SpeciesType.UNKNOWN
    }
}

private const val SPECIES_HUMAN = "human"
private const val SPECIES_HALF_GIANT = "half-giant"
private const val SPECIES_WEREWOLF = "werewolf"
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