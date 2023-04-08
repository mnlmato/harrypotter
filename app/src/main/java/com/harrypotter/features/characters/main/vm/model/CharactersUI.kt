package com.harrypotter.features.characters.main.vm.model

import java.io.Serializable

data class CharactersListUI(val list: Map<String, List<CharacterUI>>)

data class CharacterUI(
    val id: String,
    val name: String,
    val house: String,
    val imageUrl: String,
    val actorName: String,
    val gender: String,
    val species: String,
    val birth: String
) : Serializable