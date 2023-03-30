package com.harrypotter.features.characters.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harrypotter.coreui.dispatchers.CoroutinesDispatchers
import com.harrypotter.coreui.resourceprovider.ResourceProvider
import com.harrypotter.coreui.vm.SingleLiveData
import com.harrypotter.features.characters.domain.usecase.GetCharactersUseCase
import com.harrypotter.features.characters.vm.mapper.toCharactersUI
import com.harrypotter.features.characters.vm.model.CharacterUI
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

    private var showLoadingMutableEvent = MutableLiveData<Boolean>()
    val showLoadingEvent: LiveData<Boolean>
        get() = showLoadingMutableEvent

    private var charactersMutableEvent = MutableLiveData<List<CharacterUI>>()
    val charactersEvent: LiveData<List<CharacterUI>>
        get() = charactersMutableEvent

    private var showGenericErrorMutableEvent = MutableLiveData<Boolean>()
    val showGenericErrorEvent: LiveData<Boolean>
        get() = showGenericErrorMutableEvent

    val showDetailEvent = SingleLiveData<CharacterUI>()

    fun onRetryButtonClicked() {
        showGenericErrorMutableEvent.value = false
        loadCharacters()
    }

    fun loadCharacters() {
        showLoadingMutableEvent.value = true
        viewModelScope.launch(coroutinesDispatchers.immediate) {
            withContext(coroutinesDispatchers.io) {
                getCharactersUseCase().fold(
                    onSuccess = {
                        val charactersUI = it.toCharactersUI(resourceProvider)
                        charactersMutableEvent.postValue(charactersUI)
                    },
                    onError = {
                        showGenericErrorMutableEvent.postValue(true)
                    }
                )
            }
            showLoadingMutableEvent.value = false
        }
    }

    fun onItemClick(character: CharacterUI) {
        showDetailEvent.value = character
    }
}