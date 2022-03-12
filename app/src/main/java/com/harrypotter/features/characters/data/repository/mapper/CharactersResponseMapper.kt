package com.harrypotter.features.characters.data.repository.mapper

import com.harrypotter.features.characters.data.datasource.api.model.CharacterResponse
import com.harrypotter.features.characters.domain.model.*

fun List<CharacterResponse>.toCharacters() = Characters(
    list = this.map {
        Character(
            name = it.name,
            house = HouseValueResponseMapper.toType(it.house),
            imageUrl = it.imageUrl,
            actorName = it.actorName,
            gender = GenderValueResponseMapper.toType(it.gender),
            species = SpeciesValueResponseMapper.toType(it.species),
            birth = it.birth
        )
    }
)