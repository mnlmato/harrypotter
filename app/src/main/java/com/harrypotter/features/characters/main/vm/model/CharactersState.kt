package com.harrypotter.features.characters.main.vm.model

sealed class CharactersState {
    sealed class UI {
        data class Success(val characters: CharactersListUI) : CharactersState()
        data object Loading : CharactersState()
        data object Error : CharactersState()
    }

    sealed class SingleActions {
        data class ShowCharacterDetail(val id: String) : SingleActions()
        data object CloseScreen : SingleActions()
    }
}