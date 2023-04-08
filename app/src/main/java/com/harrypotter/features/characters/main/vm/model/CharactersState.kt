package com.harrypotter.features.characters.main.vm.model

sealed class CharactersState {
    data class Success(val characters: CharactersListUI) : CharactersState()
    object Loading : CharactersState()
    object Error : CharactersState()
}