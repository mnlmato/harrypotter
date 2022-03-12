package com.harrypotter.features.characters.vm.model

import androidx.annotation.StringRes
import java.io.Serializable

data class CharacterUI(
    val name: String,
    @StringRes val house: Int,
    val imageUrl: String,
    val actorName: String,
    @StringRes val gender: Int,
    @StringRes val species: Int,
    val birth: String
) : Serializable