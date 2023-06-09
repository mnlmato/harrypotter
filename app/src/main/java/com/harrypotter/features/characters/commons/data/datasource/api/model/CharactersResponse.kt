package com.harrypotter.features.characters.commons.data.datasource.api.model

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("house") val house: String,
    @SerializedName("image") val imageUrl: String,
    @SerializedName("actor") val actorName: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("species") val species: String,
    @SerializedName("dateOfBirth") val birth: String?
)