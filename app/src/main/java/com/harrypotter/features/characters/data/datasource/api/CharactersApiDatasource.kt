package com.harrypotter.features.characters.data.datasource.api

import com.harrypotter.features.characters.core.DataResult
import com.harrypotter.features.characters.data.datasource.mapper.handleNetworkExceptions
import com.harrypotter.features.characters.data.datasource.api.model.CharacterResponse
import java.lang.Exception
import javax.inject.Inject

class CharactersApiDatasource @Inject constructor(
    private val charactersApi: CharactersApi
) {
    suspend fun getCharacters(): DataResult<List<CharacterResponse>> {
        return try {
            val characters = charactersApi.getCharacters()
            DataResult.Success(characters)
        } catch (ex: Exception) {
            DataResult.Error(handleNetworkExceptions(ex))
        }
    }
}