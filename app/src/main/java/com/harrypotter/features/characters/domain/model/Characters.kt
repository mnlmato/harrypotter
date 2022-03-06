package com.harrypotter.features.characters.domain.model

data class Characters(val characters: List<Character>)

data class Character(val name: String, val house: String, val image: String)