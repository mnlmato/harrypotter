package com.harrypotter.features.characters.main.data.datasource.api

import androidx.annotation.VisibleForTesting
import com.harrypotter.features.characters.main.data.datasource.api.model.CharacterResponse
import retrofit2.http.GET

interface CharactersApi {

    @GET(CHARACTERS_ENDPOINT)
    suspend fun getCharacters(): List<CharacterResponse>
}

@VisibleForTesting
const val CHARACTERS_ENDPOINT = "characters"