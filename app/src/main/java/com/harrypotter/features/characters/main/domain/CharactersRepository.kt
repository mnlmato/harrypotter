package com.harrypotter.features.characters.main.domain

import com.harrypotter.coreapp.DataResult
import com.harrypotter.features.characters.main.domain.model.Characters

interface CharactersRepository {
    suspend fun getCharacters(): DataResult<Characters>
}