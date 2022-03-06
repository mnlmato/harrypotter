package com.harrypotter.features.characters.domain

import com.harrypotter.features.characters.core.DataResult
import com.harrypotter.features.characters.domain.model.Characters

interface CharactersRepository {
    suspend fun getCharacters(): DataResult<Characters>
}