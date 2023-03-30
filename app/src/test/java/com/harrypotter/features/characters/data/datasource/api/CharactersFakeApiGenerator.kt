package com.harrypotter.features.characters.data.datasource.api

import com.harrypotter.features.characters.data.datasource.api.model.CharacterResponse
import com.harrypotter.features.characters.domain.model.Character
import com.harrypotter.features.characters.domain.model.GenderType
import com.harrypotter.features.characters.domain.model.HouseType
import com.harrypotter.features.characters.domain.model.SpeciesType

interface CharactersFakeApiGenerator {

    fun getCharactersResponseFake() = listOf(
        CharacterResponse(
            name = "FooName1",
            house = "Gryffindor",
            imageUrl = "FooImageUrl1",
            actorName = "FooActorName1",
            gender = "Male",
            species = "human",
            birth = "01-01-1986"
        ),
        CharacterResponse(
            name = "FooName2",
            house = "Ravenclaw",
            imageUrl = "FooImageUrl2",
            actorName = "FooActorName2",
            gender = "Female",
            species = "cat",
            birth = "02-02-1986"
        ),
        CharacterResponse(
            name = "FooName3",
            house = "Hufflepuff",
            imageUrl = "FooImageUrl3",
            actorName = "FooActorName3",
            gender = "Female",
            species = "",
            birth = "03-03-1986"
        ),
        CharacterResponse(
            name = "FooName4",
            house = "",
            imageUrl = "FooImageUrl4",
            actorName = "FooActorName4",
            gender = "",
            species = "owl",
            birth = "04-04-1986"
        ),
        CharacterResponse(
            name = "FooName5",
            house = "Ravenclaw",
            imageUrl = "FooImageUrl5",
            actorName = "FooActorName5",
            gender = "Male",
            species = "Ghost",
            birth = "05-05-1986"
        ),
        CharacterResponse(
            name = "FooName6",
            house = "Slytherin",
            imageUrl = "FooImageUrl6",
            actorName = "FooActorName6",
            gender = "",
            species = "Vampire",
            birth = "06-06-1986"
        )
    )

    fun getCharactersExpected() = listOf(
        Character(
            name = "FooName1",
            house = HouseType.GRYFFINDOR,
            imageUrl = "FooImageUrl1",
            actorName = "FooActorName1",
            gender = GenderType.MALE,
            species = SpeciesType.HUMAN,
            birth = "01-01-1986"
        ),
        Character(
            name = "FooName2",
            house = HouseType.RAVENCLAW,
            imageUrl = "FooImageUrl2",
            actorName = "FooActorName2",
            gender = GenderType.FEMALE,
            species = SpeciesType.CAT,
            birth = "02-02-1986"
        ),
        Character(
            name = "FooName3",
            house = HouseType.HUFFLEPUFF,
            imageUrl = "FooImageUrl3",
            actorName = "FooActorName3",
            gender = GenderType.FEMALE,
            species = SpeciesType.UNKNOWN,
            birth = "03-03-1986"
        ),
        Character(
            name = "FooName4",
            house = HouseType.UNKNOWN,
            imageUrl = "FooImageUrl4",
            actorName = "FooActorName4",
            gender = GenderType.UNKNOWN,
            species = SpeciesType.OWL,
            birth = "04-04-1986"
        ),
        Character(
            name = "FooName5",
            house = HouseType.RAVENCLAW,
            imageUrl = "FooImageUrl5",
            actorName = "FooActorName5",
            gender = GenderType.MALE,
            species = SpeciesType.GHOST,
            birth = "05-05-1986"
        ),
        Character(
            name = "FooName6",
            house = HouseType.SLYTHERIN,
            imageUrl = "FooImageUrl6",
            actorName = "FooActorName6",
            gender = GenderType.UNKNOWN,
            species = SpeciesType.VAMPIRE,
            birth = "06-06-1986"
        )
    )
}