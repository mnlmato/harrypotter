package com.harrypotter.features.characters.vm

import com.harrypotter.features.characters.domain.model.*
import com.harrypotter.features.characters.vm.model.CharacterUI

interface CharactersFakeVMGenerator {

    fun getCharactersFake() = Characters(
        listOf(
            Character(
                name = "FooName1",
                house = HouseType.UNKNOWN,
                imageUrl = "FooImageUrl1",
                actorName = "FooActorName1",
                gender = GenderType.UNKNOWN,
                species = SpeciesType.UNKNOWN,
                birth = "01-01-1986"
            ),
            Character(
                name = "FooName2",
                house = HouseType.SLYTHERIN,
                imageUrl = "FooImageUrl2",
                actorName = "FooActorName2",
                gender = GenderType.MALE,
                species = SpeciesType.HALF_HUMAN,
                birth = "02-02-1986"
            ),
            Character(
                name = "FooName3",
                house = HouseType.GRYFFINDOR,
                imageUrl = "FooImageUrl3",
                actorName = "FooActorName3",
                gender = GenderType.FEMALE,
                species = SpeciesType.GIANT,
                birth = "03-03-1986"
            ),
            Character(
                name = "FooName4",
                house = HouseType.RAVENCLAW,
                imageUrl = "FooImageUrl4",
                actorName = "FooActorName4",
                gender = GenderType.MALE,
                species = SpeciesType.HOUSE_ELF,
                birth = "04-04-1986"
            ),
            Character(
                name = "FooName5",
                house = HouseType.HUFFLEPUFF,
                imageUrl = "FooImageUrl5",
                actorName = "FooActorName5",
                gender = GenderType.UNKNOWN,
                species = SpeciesType.CENTAUR,
                birth = "05-05-1986"
            )
        )
    )

    fun getCharactersUIExpected() = listOf(
        CharacterUI(
            name = "FooName1",
            house = "Foo",
            imageUrl = "FooImageUrl1",
            actorName = "FooActorName1",
            gender = "Foo",
            species = "Foo",
            birth = "01-01-1986"
        ),
        CharacterUI(
            name = "FooName2",
            house = "Foo",
            imageUrl = "FooImageUrl2",
            actorName = "FooActorName2",
            gender = "Foo",
            species = "Foo",
            birth = "02-02-1986"
        ),
        CharacterUI(
            name = "FooName3",
            house = "Foo",
            imageUrl = "FooImageUrl3",
            actorName = "FooActorName3",
            gender = "Foo",
            species = "Foo",
            birth = "03-03-1986"
        ),
        CharacterUI(
            name = "FooName4",
            house = "Foo",
            imageUrl = "FooImageUrl4",
            actorName = "FooActorName4",
            gender = "Foo",
            species = "Foo",
            birth = "04-04-1986"
        ),
        CharacterUI(
            name = "FooName5",
            house = "Foo",
            imageUrl = "FooImageUrl5",
            actorName = "FooActorName5",
            gender = "Foo",
            species = "Foo",
            birth = "05-05-1986"
        )
    )
}