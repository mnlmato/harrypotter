package com.harrypotter.features.characters.vm

import com.harrypotter.features.characters.domain.model.Character
import com.harrypotter.features.characters.domain.model.Characters
import com.harrypotter.features.characters.vm.model.CharacterUI

interface CharactersFakeVMGenerator {

    fun getCharactersFake() = Characters(
        listOf(
            Character(
                name = "FooName1",
                house = "",
                imageUrl = "FooImageUrl1",
                actorName = "FooActorName1",
                gender = "FooGender1",
                species = "FooSpecies1",
                birth = "01-01-1986"
            ),
            Character(
                name = "FooName2",
                house = "FooHouse2",
                imageUrl = "FooImageUrl2",
                actorName = "",
                gender = "FooGender2",
                species = "FooSpecies2",
                birth = "02-02-1986"
            ),
            Character(
                name = "FooName3",
                house = "FooHouse3",
                imageUrl = "FooImageUrl3",
                actorName = "FooActorName3",
                gender = "FooGender3",
                species = "",
                birth = "03-03-1986"
            ),
            Character(
                name = "FooName4",
                house = "FooHouse4",
                imageUrl = "FooImageUrl4",
                actorName = "FooActorName4",
                gender = "FooGender4",
                species = "FooSpecies4",
                birth = ""
            ),
            Character(
                name = "FooName5",
                house = "FooHouse5",
                imageUrl = "FooImageUrl5",
                actorName = "FooActorName5",
                gender = "",
                species = "FooSpecies5",
                birth = "05-05-1986"
            )
        )
    )

    fun getCharactersUIExpected() = listOf(
        CharacterUI(
            name = "FooName1",
            house = "Unknown",
            imageUrl = "FooImageUrl1",
            actorName = "FooActorName1",
            gender = "FooGender1",
            species = "FooSpecies1",
            birth = "01-01-1986"
        ),
        CharacterUI(
            name = "FooName2",
            house = "FooHouse2",
            imageUrl = "FooImageUrl2",
            actorName = "Unknown",
            gender = "FooGender2",
            species = "FooSpecies2",
            birth = "02-02-1986"
        ),
        CharacterUI(
            name = "FooName3",
            house = "FooHouse3",
            imageUrl = "FooImageUrl3",
            actorName = "FooActorName3",
            gender = "FooGender3",
            species = "Unknown",
            birth = "03-03-1986"
        ),
        CharacterUI(
            name = "FooName4",
            house = "FooHouse4",
            imageUrl = "FooImageUrl4",
            actorName = "FooActorName4",
            gender = "FooGender4",
            species = "FooSpecies4",
            birth = "Unknown"
        ),
        CharacterUI(
            name = "FooName5",
            house = "FooHouse5",
            imageUrl = "FooImageUrl5",
            actorName = "FooActorName5",
            gender = "Unknown",
            species = "FooSpecies5",
            birth = "05-05-1986"
        )
    )
}