package com.harrypotter.features.characters.vm.mapper

import com.harrypotter.R
import com.harrypotter.coreui.resourceprovider.ResourceProvider
import com.harrypotter.features.characters.domain.model.Characters
import com.harrypotter.features.characters.vm.model.CharacterUI

fun Characters.toCharactersUI(resourceProvider: ResourceProvider) = list.map {
    CharacterUI(
        name = it.name,
        house = it.house.mapToString(resourceProvider),
        imageUrl = it.imageUrl,
        actorName = it.actorName,
        gender = it.gender.mapToString(resourceProvider),
        species = it.species.mapToString(resourceProvider),
        birth = it.birth.ifBlank { resourceProvider.getString(R.string.birthday_unknown) }
    )
}