package com.harrypotter.features.characters.main.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harrypotter.coreui.dispatchers.CoroutinesDispatchers
import com.harrypotter.coreui.resourceprovider.ResourceProvider
import com.harrypotter.coreui.vm.ClickThrottler
import com.harrypotter.coreui.vm.SingleLiveData
import com.harrypotter.features.characters.main.domain.usecase.GetCharactersUseCase
import com.harrypotter.features.characters.main.vm.mapper.toCharactersUI
import com.harrypotter.features.characters.main.vm.model.CharacterUI
import com.harrypotter.features.characters.main.vm.model.CharactersActionsFromUI
import com.harrypotter.features.characters.main.vm.model.CharactersState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val resourceProvider: ResourceProvider,
    private val clickThrottler: ClickThrottler,
    private val coroutinesDispatchers: CoroutinesDispatchers
) : ViewModel() {

    val actionsSingleEvents = SingleLiveData<CharactersState.SingleActions>()

    private val charactersStateMutableEvent =
        MutableLiveData<CharactersState>(CharactersState.UI.Loading)
    val charactersStateEvent: LiveData<CharactersState>
        get() = charactersStateMutableEvent

    fun onCLickAction(action: CharactersActionsFromUI) {
        when (action) {
            is CharactersActionsFromUI.RetryButtonClick -> loadCharacters()
            is CharactersActionsFromUI.ItemListClick -> onItemClick(action.character)
            is CharactersActionsFromUI.OnBackClick -> onBackButtonClick()
        }
    }

    fun loadCharacters() {
        charactersStateMutableEvent.value = CharactersState.UI.Loading
        viewModelScope.launch(coroutinesDispatchers.immediate) {
            withContext(coroutinesDispatchers.io) {
                getCharactersUseCase().fold(
                    onSuccess = {
                        val charactersListUI = it.toCharactersUI(resourceProvider)
                        val successState = CharactersState.UI.Success(charactersListUI)
                        charactersStateMutableEvent.postValue(successState)
                    },
                    onError = {
                        charactersStateMutableEvent.postValue(CharactersState.UI.Error)
                    }
                )
            }
        }
    }

    private fun onItemClick(character: CharacterUI) {
        viewModelScope.launch(coroutinesDispatchers.main) {
            clickThrottler.onClick {
                actionsSingleEvents.value =
                    CharactersState.SingleActions.ShowCharacterDetail(character.id)
            }
        }
    }

    private fun onBackButtonClick() {
        actionsSingleEvents.value = CharactersState.SingleActions.CloseScreen
    }
}