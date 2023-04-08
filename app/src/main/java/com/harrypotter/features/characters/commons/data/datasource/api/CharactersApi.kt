package com.harrypotter.features.characters.commons.data.datasource.api

import com.harrypotter.features.characters.commons.data.datasource.api.model.CharacterResponse
import retrofit2.http.GET

interface CharactersApi {

    @GET(CHARACTERS_ENDPOINT)
    suspend fun getCharacters(): List<CharacterResponse>
}

private const val CHARACTERS_ENDPOINT = "characters"