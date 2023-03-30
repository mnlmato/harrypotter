package com.harrypotter.features.characters.domain.usecase

import com.harrypotter.features.characters.domain.model.*

interface CharactersFakeDomainGenerator {

    fun getCharactersFake() = Characters(
        listOf(
            Character(
                name = "FooName1",
                house = HouseType.SLYTHERIN,
                imageUrl = "FooImageUrl1",
                actorName = "FooActorName1",
                gender = GenderType.UNKNOWN,
                species = SpeciesType.VAMPIRE,
                birth = "01-01-1986"
            ),
            Character(
                name = "",
                house = HouseType.RAVENCLAW,
                imageUrl = "FooImageUrl2",
                actorName = "FooActorName2",
                gender = GenderType.MALE,
                species = SpeciesType.OWL,
                birth = "02-02-1986"
            ),
            Character(
                name = " ",
                house = HouseType.HUFFLEPUFF,
                imageUrl = "FooImageUrl3",
                actorName = "FooActorName3",
                gender = GenderType.MALE,
                species = SpeciesType.UNKNOWN,
                birth = "03-03-1986"
            ),
            Character(
                name = "FooName4",
                house = HouseType.RAVENCLAW,
                imageUrl = "FooImageUrl4",
                actorName = "FooActorName4",
                gender = GenderType.FEMALE,
                species = SpeciesType.HUMAN,
                birth = "04-04-1986"
            ),
            Character(
                name = "FooName5",
                house = HouseType.GRYFFINDOR,
                imageUrl = "FooImageUrl5",
                actorName = "FooActorName5",
                gender = GenderType.MALE,
                species = SpeciesType.HIPPOGRIFF,
                birth = "05-05-1986"
            ),
            Character(
                name = "FooName6",
                house = HouseType.GRYFFINDOR,
                imageUrl = "FooImageUrl6",
                actorName = "FooActorName6",
                gender = GenderType.UNKNOWN,
                species = SpeciesType.HUMAN,
                birth = "06-06-1986"
            )
        )
    )

    fun getCharactersExpected() = Characters(
        listOf(
            Character(
                name = "FooName1",
                house = HouseType.SLYTHERIN,
                imageUrl = "FooImageUrl1",
                actorName = "FooActorName1",
                gender = GenderType.UNKNOWN,
                species = SpeciesType.VAMPIRE,
                birth = "01-01-1986"
            ),
            Character(
                name = "FooName4",
                house = HouseType.RAVENCLAW,
                imageUrl = "FooImageUrl4",
                actorName = "FooActorName4",
                gender = GenderType.FEMALE,
                species = SpeciesType.HUMAN,
                birth = "04-04-1986"
            ),
            Character(
                name = "FooName5",
                house = HouseType.GRYFFINDOR,
                imageUrl = "FooImageUrl5",
                actorName = "FooActorName5",
                gender = GenderType.MALE,
                species = SpeciesType.HIPPOGRIFF,
                birth = "05-05-1986"
            ),
            Character(
                name = "FooName6",
                house = HouseType.GRYFFINDOR,
                imageUrl = "FooImageUrl6",
                actorName = "FooActorName6",
                gender = GenderType.UNKNOWN,
                species = SpeciesType.HUMAN,
                birth = "06-06-1986"
            )
        )
    )
}