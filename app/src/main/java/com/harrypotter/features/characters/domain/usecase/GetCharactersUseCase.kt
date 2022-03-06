package com.harrypotter.features.characters.domain.usecase

import com.harrypotter.features.characters.domain.CharactersRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository
) {
    suspend operator fun invoke() = charactersRepository.getCharacters()
}