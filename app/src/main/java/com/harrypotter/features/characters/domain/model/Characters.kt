package com.harrypotter.features.characters.domain.model

data class Characters(val list: List<Character>)

data class Character(
    val name: String,
    val house: String,
    val imageUrl: String,
    val actorName: String,
    val gender: String,
    val species: String,
    val birth: String
)