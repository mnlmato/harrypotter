package com.harrypotter.features.characters.main.domain

import com.harrypotter.coreapp.DataResult
import com.harrypotter.features.characters.main.domain.model.Characters
import com.harrypotter.features.characters.main.domain.model.Character as CharacterHarryPotter

interface CharactersRepository {
    suspend fun getCharacters(): DataResult<Characters>
    fun getCharacter(id: String): CharacterHarryPotter
}