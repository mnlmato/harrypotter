package com.harrypotter.features.characters.domain.usecase

import com.harrypotter.features.characters.domain.model.Character
import com.harrypotter.features.characters.domain.model.Characters

interface CharactersFakeDomainGenerator {

    fun getCharactersFake() = Characters(
        listOf(
            Character(
                name = "FooName1",
                house = "FooHouse1",
                imageUrl = "FooImageUrl1",
                actorName = "FooActorName1",
                gender = "FooGender1",
                species = "FooSpecies1",
                birth = "01-01-1986"
            ),
            Character(
                name = "",
                house = "FooHouse2",
                imageUrl = "FooImageUrl2",
                actorName = "FooActorName2",
                gender = "FooGender2",
                species = "FooSpecies2",
                birth = "02-02-1986"
            ),
            Character(
                name = " ",
                house = "FooHouse3",
                imageUrl = "FooImageUrl3",
                actorName = "FooActorName3",
                gender = "FooGender3",
                species = "FooSpecies3",
                birth = "03-03-1986"
            ),
            Character(
                name = "FooName4",
                house = "FooHouse4",
                imageUrl = "FooImageUrl4",
                actorName = "FooActorName4",
                gender = "FooGender4",
                species = "FooSpecies4",
                birth = "04-04-1986"
            ),
            Character(
                name = "FooName5",
                house = "FooHouse5",
                imageUrl = "FooImageUrl5",
                actorName = "FooActorName5",
                gender = "FooGender5",
                species = "FooSpecies5",
                birth = "05-05-1986"
            ),
            Character(
                name = "FooName6",
                house = "FooHouse6",
                imageUrl = "FooImageUrl6",
                actorName = "FooActorName6",
                gender = "FooGender6",
                species = "FooSpecies6",
                birth = "06-06-1986"
            )
        )
    )

    fun getCharactersExpected() = Characters(
        listOf(
            Character(
                name = "FooName1",
                house = "FooHouse1",
                imageUrl = "FooImageUrl1",
                actorName = "FooActorName1",
                gender = "FooGender1",
                species = "FooSpecies1",
                birth = "01-01-1986"
            ),
            Character(
                name = "FooName4",
                house = "FooHouse4",
                imageUrl = "FooImageUrl4",
                actorName = "FooActorName4",
                gender = "FooGender4",
                species = "FooSpecies4",
                birth = "04-04-1986"
            ),
            Character(
                name = "FooName5",
                house = "FooHouse5",
                imageUrl = "FooImageUrl5",
                actorName = "FooActorName5",
                gender = "FooGender5",
                species = "FooSpecies5",
                birth = "05-05-1986"
            ),
            Character(
                name = "FooName6",
                house = "FooHouse6",
                imageUrl = "FooImageUrl6",
                actorName = "FooActorName6",
                gender = "FooGender6",
                species = "FooSpecies6",
                birth = "06-06-1986"
            )
        )
    )
}