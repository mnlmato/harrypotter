package com.harrypotter.features.characters.main.vm.model

sealed class CharactersActionsFromUI {
    data class ItemListClick(val character: CharacterUI) : CharactersActionsFromUI()
    data object RetryButtonClick : CharactersActionsFromUI()
    data object OnBackClick : CharactersActionsFromUI()
}