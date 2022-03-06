package com.harrypotter.features.characters.vm.mapper

import com.harrypotter.R
import com.harrypotter.coreui.stringconverter.StringConverter
import com.harrypotter.features.characters.domain.model.Characters
import com.harrypotter.features.characters.vm.model.CharacterUI

fun Characters.toCharactersUI(stringConverter: StringConverter) = list.map {
    CharacterUI(
        name = it.name,
        house = it.house.ifEmpty { stringConverter.convert(R.string.characters_unknown_house) },
        imageUrl = it.imageUrl,
        actorName = it.actorName.ifEmpty { stringConverter.convert(R.string.characters_unknown_actor_name) },
        gender = it.gender.ifEmpty { stringConverter.convert(R.string.characters_unknown_gender) },
        species = it.species.ifEmpty { stringConverter.convert(R.string.characters_unknown_species) },
        birth = it.birth.ifEmpty { stringConverter.convert(R.string.characters_unknown_birth) }
    )
}