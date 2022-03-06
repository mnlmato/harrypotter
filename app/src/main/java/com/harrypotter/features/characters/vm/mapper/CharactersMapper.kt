package com.harrypotter.features.characters.vm.mapper

import com.harrypotter.R
import com.harrypotter.coreui.stringconverter.StringConverter
import com.harrypotter.features.characters.domain.model.Characters
import com.harrypotter.features.characters.vm.model.CharacterUI

fun Characters.toCharactersUI(stringConverter: StringConverter) = data.map {
    CharacterUI(
        name = it.name,
        house = it.house.ifEmpty { stringConverter.convert(R.string.characters_unknown_house) },
        imageUrl = it.imageUrl
    )
}