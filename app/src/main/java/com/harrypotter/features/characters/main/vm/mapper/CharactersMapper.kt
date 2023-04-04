package com.harrypotter.features.characters.main.vm.mapper

import com.harrypotter.R
import com.harrypotter.coreui.resourceprovider.ResourceProvider
import com.harrypotter.features.characters.main.domain.model.Character
import com.harrypotter.features.characters.main.domain.model.Characters
import com.harrypotter.features.characters.main.vm.model.CharacterUI
import com.harrypotter.features.characters.main.vm.model.CharactersListUI

fun Characters.toCharactersUI(
    resourceProvider: ResourceProvider
): CharactersListUI {
    val list = list.asSequence().map {
        it.toCharacterUI(resourceProvider)
    }.groupBy {
        it.house
    }
    return CharactersListUI(list)
}

fun Character.toCharacterUI(
    resourceProvider: ResourceProvider
): CharacterUI {
    return CharacterUI(
        id = id,
        name = name,
        house = house.mapToString(resourceProvider),
        imageUrl = imageUrl,
        actorName = actorName,
        gender = gender.mapToString(resourceProvider),
        species = species.mapToString(resourceProvider),
        birth = birth.ifBlank { resourceProvider.getString(R.string.birthday_unknown) }
    )
}