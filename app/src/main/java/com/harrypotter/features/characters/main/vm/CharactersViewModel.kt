package com.harrypotter.features.characters.main.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harrypotter.coreui.dispatchers.CoroutinesDispatchers
import com.harrypotter.coreui.resourceprovider.ResourceProvider
import com.harrypotter.coreui.vm.CustomThrottler
import com.harrypotter.features.characters.main.domain.usecase.GetCharactersUseCase
import com.harrypotter.features.characters.main.vm.mapper.toCharactersUI
import com.harrypotter.features.characters.main.vm.model.CharacterUI
import com.harrypotter.features.characters.main.vm.model.CharactersState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val clickThrottler: CustomThrottler,
    private val resourceProvider: ResourceProvider,
    private val coroutinesDispatchers: CoroutinesDispatchers,
) : ViewModel() {

    private val _charactersUIEvent =
        MutableStateFlow<CharactersState.UI>(CharactersState.UI.Loading)

    private val _charactersActionsEvent = MutableSharedFlow<CharactersState.Actions>()

    val charactersEvent: Flow<CharactersState> = merge(_charactersUIEvent, _charactersActionsEvent)

    fun onRetryButtonClicked() {
        loadCharacters()
    }

    fun loadCharacters() {
        viewModelScope.launch(coroutinesDispatchers.main) {
            withContext(coroutinesDispatchers.io) {
                getCharactersUseCase().fold(
                    onSuccess = {
                        val charactersListUI = it.toCharactersUI(resourceProvider)
                        val successState = CharactersState.UI.Success(charactersListUI)
                        _charactersUIEvent.emit(successState)
                    },
                    onError = {
                        _charactersUIEvent.emit(CharactersState.UI.Error)
                    }
                )
            }
        }
    }

    fun onItemClick(character: CharacterUI) {
        viewModelScope.launch(coroutinesDispatchers.main) {
            clickThrottler.onClick {
                val event = CharactersState.Actions.NavigateDetail(character.id)
                _charactersActionsEvent.emit(event)
            }
        }
    }
}