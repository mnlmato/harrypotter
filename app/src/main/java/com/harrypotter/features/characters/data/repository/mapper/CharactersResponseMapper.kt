package com.harrypotter.features.characters.data.repository.mapper

import com.harrypotter.features.characters.data.datasource.api.model.CharacterResponse
import com.harrypotter.features.characters.domain.model.*

fun List<CharacterResponse>.toCharacters() = Characters(
    list = this.map {
        Character(
            name = it.name,
            house = HouseResponseMapper(it.house).toType(),
            imageUrl = it.imageUrl,
            actorName = it.actorName,
            gender = GenderResponseMapper(it.gender).toType(),
            species = SpecieResponseMapper(it.species).toType(),
            birth = it.birth
        )
    }
)