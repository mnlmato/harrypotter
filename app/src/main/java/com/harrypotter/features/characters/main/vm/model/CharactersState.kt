package com.harrypotter.features.characters.main.vm.model

sealed class CharactersState {
    sealed class UI : CharactersState() {
        data class Success(val characters: CharactersListUI) : UI()
        object Loading : UI()
        object Error : UI()
    }

    sealed class Actions : CharactersState() {
        data class NavigateDetail(val characterId: String) : Actions()
    }
}