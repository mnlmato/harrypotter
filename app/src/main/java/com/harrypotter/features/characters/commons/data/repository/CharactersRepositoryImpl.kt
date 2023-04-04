package com.harrypotter.features.characters.commons.data.repository

import com.harrypotter.coreapp.DataResult
import com.harrypotter.features.characters.commons.data.datasource.api.CharactersApiDatasource
import com.harrypotter.features.characters.commons.data.datasource.cache.CharactersCacheDatasource
import com.harrypotter.features.characters.commons.data.repository.mapper.toCharacter
import com.harrypotter.features.characters.commons.data.repository.mapper.toCharacters
import com.harrypotter.features.characters.main.domain.CharactersRepository
import com.harrypotter.features.characters.main.domain.model.Characters
import com.harrypotter.features.characters.main.domain.model.Character
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val charactersApiDatasource: CharactersApiDatasource,
    private val charactersCacheDatasource: CharactersCacheDatasource,
) : CharactersRepository {

    override suspend fun getCharacters(): DataResult<Characters> {
        val charactersResult = charactersApiDatasource.getCharacters()
        return if (charactersResult is DataResult.Success) {
            val charactersFromApi = charactersResult.data
            charactersCacheDatasource.save(charactersFromApi)
            DataResult.Success(charactersFromApi.toCharacters())
        } else {
            charactersResult as DataResult.Error
        }
    }

    override fun getCharacter(id: String): Character {
        return charactersCacheDatasource.getCharacter(id).toCharacter()
    }
}