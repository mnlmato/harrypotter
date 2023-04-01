package com.harrypotter.features.characters.main.vm.mapper

import com.harrypotter.R
import com.harrypotter.coreui.resourceprovider.ResourceProvider
import com.harrypotter.features.characters.main.domain.model.Characters
import com.harrypotter.features.characters.main.vm.model.CharacterUI
import com.harrypotter.features.characters.main.vm.model.CharactersListUI

fun Characters.toCharactersUI(
    resourceProvider: ResourceProvider
): CharactersListUI {
    val list = list.asSequence().map {
        CharacterUI(
            name = it.name,
            house = it.house.mapToString(resourceProvider),
            imageUrl = it.imageUrl,
            actorName = it.actorName,
            gender = it.gender.mapToString(resourceProvider),
            species = it.species.mapToString(resourceProvider),
            birth = it.birth.ifBlank { resourceProvider.getString(R.string.birthday_unknown) }
        )
    }.groupBy {
        it.house
    }
    return CharactersListUI(list)
}
