package com.harrypotter.features.characters.detail.domain.usecase

import com.harrypotter.features.characters.main.domain.CharactersRepository
import  com.harrypotter.features.characters.main.domain.model.Character
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository,
) {
    operator fun invoke(id: String): Character {
        return charactersRepository.getCharacter(id)
    }
}