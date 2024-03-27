package com.harrypotter.features.characters.commons.data.repository

import com.harrypotter.coreapp.DataResult
import com.harrypotter.coreapp.exceptions.UnavailableServerException
import com.harrypotter.features.characters.commons.data.datasource.api.CharactersApiDatasource
import com.harrypotter.features.characters.commons.data.datasource.api.model.CharacterResponse
import com.harrypotter.features.characters.commons.data.datasource.cache.CharactersCacheDatasource
import com.harrypotter.features.characters.commons.data.datasource.local.CharactersLocalDatasource
import com.harrypotter.features.characters.commons.data.repository.mapper.toCharacter
import com.harrypotter.features.characters.commons.data.repository.mapper.toCharacters
import com.harrypotter.features.characters.main.domain.CharactersRepository
import com.harrypotter.features.characters.main.domain.model.Character
import com.harrypotter.features.characters.main.domain.model.Characters
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val charactersApiDatasource: CharactersApiDatasource,
    private val charactersCacheDatasource: CharactersCacheDatasource,
    private val charactersLocalDatasource: CharactersLocalDatasource,
) : CharactersRepository {

    override suspend fun getCharacters(): DataResult<Characters> {
        val charactersResult = charactersApiDatasource.getCharacters()
        return if (charactersResult is DataResult.Success) {
            val charactersFromApi = charactersResult.data
            handleSuccessResult(charactersFromApi)
        } else {
            val error = charactersResult as DataResult.Error
            when (error.ex) {
                is UnavailableServerException -> handleUnavailableServerException(error)
                else -> error
            }
        }
    }

    private fun handleUnavailableServerException(error: DataResult.Error): DataResult<Characters> {
        val charactersFromLocalDatasource = charactersLocalDatasource.getCharacters()
        return if (charactersFromLocalDatasource.isEmpty()) {
            error
        } else {
            handleSuccessResult(charactersList = charactersFromLocalDatasource)
        }
    }

    private fun handleSuccessResult(charactersList: List<CharacterResponse>): DataResult<Characters> {
        charactersCacheDatasource.save(charactersList)
        return DataResult.Success(charactersList.toCharacters())
    }

    override fun getCharacter(id: String): Character {
        return charactersCacheDatasource.getCharacter(id).toCharacter()
    }
}