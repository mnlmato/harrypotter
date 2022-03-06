package com.harrypotter.features.characters.vm.model

import java.io.Serializable

data class CharacterUI(
    val name: String,
    val house: String,
    val imageUrl: String,
    val actorName: String,
    val gender: String,
    val species: String,
    val birth: String
) : Serializable