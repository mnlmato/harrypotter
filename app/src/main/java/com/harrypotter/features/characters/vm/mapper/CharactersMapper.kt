package com.harrypotter.features.characters.vm.mapper

import com.harrypotter.features.characters.domain.model.Characters
import com.harrypotter.features.characters.vm.model.CharacterUI

fun Characters.toCharactersUI() = list.map {
    CharacterUI(
        name = it.name,
        house = it.house.toStringRes(),
        imageUrl = it.imageUrl,
        actorName = it.actorName,
        gender = it.gender.toStringRes(),
        species = it.species.toStringRes(),
        birth = it.birth
    )
}