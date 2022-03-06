package com.harrypotter.features.characters.data.datasource.api

import com.harrypotter.features.characters.data.datasource.api.model.CharacterResponse
import retrofit2.http.GET

interface CharactersApi {

    @GET("characters")
    suspend fun getCharacters(): List<CharacterResponse>
}