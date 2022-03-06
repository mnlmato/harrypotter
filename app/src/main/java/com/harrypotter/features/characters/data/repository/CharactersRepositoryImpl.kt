package com.harrypotter.features.characters.data.repository

import com.harrypotter.coreapp.DataResult
import com.harrypotter.features.characters.data.datasource.api.CharactersApiDatasource
import com.harrypotter.features.characters.data.repository.mapper.toCharacters
import com.harrypotter.features.characters.domain.CharactersRepository
import com.harrypotter.features.characters.domain.model.Characters
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val charactersApiDatasource: CharactersApiDatasource
) : CharactersRepository {

    override suspend fun getCharacters(): DataResult<Characters> {
        val charactersResult = charactersApiDatasource.getCharacters()

        return if (charactersResult is DataResult.Success) {
            val characters = charactersResult.data.toCharacters()
            DataResult.Success(characters)
        } else {
            charactersResult as DataResult.Error
        }
    }
}
