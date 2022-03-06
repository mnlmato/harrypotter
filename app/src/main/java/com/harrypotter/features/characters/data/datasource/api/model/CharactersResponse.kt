package com.harrypotter.features.characters.data.datasource.api.model

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("name") val name: String,
    @SerializedName("house") val house: String,
    @SerializedName("image") val imageUrl: String
)