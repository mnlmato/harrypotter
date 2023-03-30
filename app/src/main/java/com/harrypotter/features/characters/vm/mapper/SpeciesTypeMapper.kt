package com.harrypotter.features.characters.vm.mapper

import com.harrypotter.R
import com.harrypotter.coreui.resourceprovider.ResourceProvider
import com.harrypotter.features.characters.domain.model.SpeciesType

fun SpeciesType.mapToString(resourceProvider: ResourceProvider): String {
    val speciesResId = when (this) {
        SpeciesType.HUMAN -> R.string.species_human
        SpeciesType.HALF_GIANT -> R.string.species_half_giant
        SpeciesType.WEREWOLF -> R.string.species_werewolf
        SpeciesType.CAT -> R.string.species_cat
        SpeciesType.GOBLIN -> R.string.species_goblin
        SpeciesType.OWL -> R.string.species_owl
        SpeciesType.GHOST -> R.string.species_ghost
        SpeciesType.POLTERGEIST -> R.string.species_poltergeist
        SpeciesType.THREE_HEADED_DOG -> R.string.species_three_headed_dog
        SpeciesType.DRAGON -> R.string.species_dragon
        SpeciesType.CENTAUR -> R.string.species_centaur
        SpeciesType.HOUSE_ELF -> R.string.species_house_elf
        SpeciesType.ACROMANTULA -> R.string.species_acromantula
        SpeciesType.HIPPOGRIFF -> R.string.species_hippogriff
        SpeciesType.GIANT -> R.string.species_giant
        SpeciesType.VAMPIRE -> R.string.species_vampire
        SpeciesType.HALF_HUMAN -> R.string.species_half_human
        SpeciesType.UNKNOWN -> R.string.species_unkwon
    }
    return resourceProvider.getString(speciesResId)
}