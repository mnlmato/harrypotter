package com.harrypotter.features.characters.commons.data.datasource.cache

import com.harrypotter.features.characters.commons.data.datasource.api.model.CharacterResponse
import javax.inject.Inject

class CharactersCacheDatasource @Inject constructor() {

    private var characters: List<CharacterResponse>? = null

    fun save(charactersListResponse: List<CharacterResponse>) {
        this.characters = charactersListResponse
    }

    /**
     *  @throws IllegalArgumentException if the data is fetched before to init the cache,
     *  but this result it won't be possible because the CharactersRepository is doing a right flow
     * */
    fun getCharacter(id: String): CharacterResponse {
        return characters?.firstOrNull { id == it.id } ?: throw IllegalArgumentException(
            "id doesn't exist in the cache.  Init the cache before to fetch data."
        )
    }
}