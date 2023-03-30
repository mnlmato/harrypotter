package com.harrypotter.features.characters.main.data.repository.mapper

import com.harrypotter.features.characters.main.data.datasource.api.model.CharacterResponse
import com.harrypotter.features.characters.main.domain.model.Character
import com.harrypotter.features.characters.main.domain.model.Characters

fun List<CharacterResponse>.toCharacters() = Characters(
    list = this.map {
        Character(
            name = it.name,
            house = HouseValueResponseMapper.toType(it.house),
            imageUrl = it.imageUrl,
            actorName = it.actorName,
            gender = GenderValueResponseMapper.toType(it.gender),
            species = SpeciesValueResponseMapper.toType(it.species),
            birth = it.birth ?: ""
        )
    }
)