package com.harrypotter.features.characters.commons.data.repository.mapper

import com.harrypotter.features.characters.commons.data.datasource.api.model.CharacterResponse
import com.harrypotter.features.characters.main.domain.model.Character
import com.harrypotter.features.characters.main.domain.model.Characters

fun List<CharacterResponse>.toCharacters() = Characters(
    list = this.map {
        it.toCharacter()
    }
)

fun CharacterResponse.toCharacter(): Character {
    return Character(
        id = id,
        name = name,
        house = HouseValueResponseMapper.toType(house),
        imageUrl = imageUrl,
        actorName = actorName,
        gender = GenderValueResponseMapper.toType(gender),
        species = SpeciesValueResponseMapper.toType(species),
        birth = birth.orEmpty(),
    )
}