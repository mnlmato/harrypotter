package com.harrypotter.features.characters.main.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harrypotter.coreui.dispatchers.CoroutinesDispatchers
import com.harrypotter.coreui.resourceprovider.ResourceProvider
import com.harrypotter.coreui.vm.SingleLiveData
import com.harrypotter.features.characters.main.domain.usecase.GetCharactersUseCase
import com.harrypotter.features.characters.main.vm.mapper.toCharactersUI
import com.harrypotter.features.characters.main.vm.model.CharacterUI
import com.harrypotter.features.characters.main.vm.model.CharactersState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val resourceProvider: ResourceProvider,
    private val coroutinesDispatchers: CoroutinesDispatchers
) : ViewModel() {

    val showDetailEvent = SingleLiveData<String>()

    private val charactersStateMutableEvent =
        MutableLiveData<CharactersState>(CharactersState.Loading)
    val charactersStateEvent: LiveData<CharactersState>
        get() = charactersStateMutableEvent

    fun onRetryButtonClicked() {
        loadCharacters()
    }

    fun loadCharacters() {
        charactersStateMutableEvent.value = CharactersState.Loading
        viewModelScope.launch(coroutinesDispatchers.immediate) {
            withContext(coroutinesDispatchers.io) {
                getCharactersUseCase().fold(
                    onSuccess = {
                        val charactersListUI = it.toCharactersUI(resourceProvider)
                        val successState = CharactersState.Success(charactersListUI)
                        charactersStateMutableEvent.postValue(successState)
                    },
                    onError = {
                        charactersStateMutableEvent.postValue(CharactersState.Error)
                    }
                )
            }
        }
    }

    fun onItemClick(character: CharacterUI) {
        showDetailEvent.value = character.id
    }
}