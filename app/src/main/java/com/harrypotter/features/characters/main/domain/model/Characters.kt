package com.harrypotter.features.characters.main.domain.model

data class Characters(val list: List<Character>)

data class Character(
    val name: String,
    val house: HouseType,
    val imageUrl: String,
    val actorName: String,
    val gender: GenderType,
    val species: SpeciesType,
    val birth: String
)

enum class HouseType {
    GRYFFINDOR,
    SLYTHERIN,
    HUFFLEPUFF,
    RAVENCLAW,
    UNKNOWN
}

enum class GenderType {
    MALE,
    FEMALE,
    UNKNOWN
}

enum class SpeciesType {
    HUMAN,
    HALF_GIANT,
    WEREWOLF,
    CAT,
    GOBLIN,
    OWL,
    GHOST,
    POLTERGEIST,
    THREE_HEADED_DOG,
    DRAGON,
    CENTAUR,
    HOUSE_ELF,
    ACROMANTULA,
    HIPPOGRIFF,
    GIANT,
    VAMPIRE,
    HALF_HUMAN,
    UNKNOWN
}