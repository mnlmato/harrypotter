package com.harrypotter.features.characters.data.repository.mapper

import com.harrypotter.features.characters.data.datasource.api.model.CharacterResponse
import com.harrypotter.features.characters.domain.model.Character
import com.harrypotter.features.characters.domain.model.Characters

fun List<CharacterResponse>.toCharacters() = Characters(
    data = this.map {
        Character(
            name = it.name,
            house = it.house,
            imageUrl = it.imageUrl
        )
    }
)