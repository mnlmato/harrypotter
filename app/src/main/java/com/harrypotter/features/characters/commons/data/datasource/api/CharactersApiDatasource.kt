package com.harrypotter.features.characters.commons.data.datasource.api

import com.harrypotter.coreapp.DataResult
import com.harrypotter.coredata.mapper.toCustomException
import com.harrypotter.features.characters.commons.data.datasource.api.model.CharacterResponse
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
            DataResult.Error(ex.toCustomException())
        }
    }
}