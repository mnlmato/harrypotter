package com.harrypotter.features.characters.main.domain.usecase

import com.harrypotter.features.characters.main.domain.model.*

interface CharactersFakeDomainGenerator {

    fun getCharactersFake() = Characters(
        listOf(
            Character(
                id = "FooId1",
                name = "FooName1",
                house = HouseType.SLYTHERIN,
                imageUrl = "FooImageUrl1",
                actorName = "FooActorName1",
                gender = GenderType.UNKNOWN,
                species = SpeciesType.VAMPIRE,
                birth = "01-01-1986"
            ),
            Character(
                id = "FooId2",
                name = "",
                house = HouseType.RAVENCLAW,
                imageUrl = "FooImageUrl2",
                actorName = "FooActorName2",
                gender = GenderType.MALE,
                species = SpeciesType.OWL,
                birth = "02-02-1986"
            ),
            Character(
                id = "FooId3",
                name = " ",
                house = HouseType.HUFFLEPUFF,
                imageUrl = "FooImageUrl3",
                actorName = "FooActorName3",
                gender = GenderType.MALE,
                species = SpeciesType.UNKNOWN,
                birth = "03-03-1986"
            ),
            Character(
                id = "FooId4",
                name = "FooName4",
                house = HouseType.RAVENCLAW,
                imageUrl = "FooImageUrl4",
                actorName = "FooActorName4",
                gender = GenderType.FEMALE,
                species = SpeciesType.HUMAN,
                birth = "04-04-1986"
            ),
            Character(
                id = "FooId5",
                name = "FooName5",
                house = HouseType.GRYFFINDOR,
                imageUrl = "FooImageUrl5",
                actorName = "FooActorName5",
                gender = GenderType.MALE,
                species = SpeciesType.HIPPOGRIFF,
                birth = "05-05-1986"
            ),
            Character(
                id = "FooId6",
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
                id = "FooId1",
                name = "FooName1",
                house = HouseType.SLYTHERIN,
                imageUrl = "FooImageUrl1",
                actorName = "FooActorName1",
                gender = GenderType.UNKNOWN,
                species = SpeciesType.VAMPIRE,
                birth = "01-01-1986"
            ),
            Character(
                id = "FooId4",
                name = "FooName4",
                house = HouseType.RAVENCLAW,
                imageUrl = "FooImageUrl4",
                actorName = "FooActorName4",
                gender = GenderType.FEMALE,
                species = SpeciesType.HUMAN,
                birth = "04-04-1986"
            ),
            Character(
                id = "FooId5",
                name = "FooName5",
                house = HouseType.GRYFFINDOR,
                imageUrl = "FooImageUrl5",
                actorName = "FooActorName5",
                gender = GenderType.MALE,
                species = SpeciesType.HIPPOGRIFF,
                birth = "05-05-1986"
            ),
            Character(
                id = "FooId6",
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