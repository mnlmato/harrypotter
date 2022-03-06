package com.harrypotter.features.characters.domain

import com.harrypotter.coreapp.DataResult
import com.harrypotter.features.characters.domain.model.Characters

interface CharactersRepository {
    suspend fun getCharacters(): DataResult<Characters>
}