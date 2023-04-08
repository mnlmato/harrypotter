package com.harrypotter.features.characters.main.domain.usecase

import com.harrypotter.coreapp.DataResult
import com.harrypotter.features.characters.main.domain.CharactersRepository
import com.harrypotter.features.characters.main.domain.model.Characters
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository
) {
    suspend operator fun invoke(): DataResult<Characters> {
        val charactersResult = charactersRepository.getCharacters()
        return if (charactersResult is DataResult.Success) {
            val charactersValidResult = charactersResult.data.list.filter { it.name.isNotBlank() }
            DataResult.Success(Characters(charactersValidResult))
        } else {
            charactersResult as DataResult.Error
        }
    }
}