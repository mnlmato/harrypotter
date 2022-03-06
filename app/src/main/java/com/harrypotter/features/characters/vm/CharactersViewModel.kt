package com.harrypotter.features.characters.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harrypotter.coreui.dispatchers.CoroutinesDispatchers
import com.harrypotter.coreui.stringconverter.StringConverter
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
    private val stringConverter: StringConverter,
    private val coroutinesDispatchers: CoroutinesDispatchers
) : ViewModel() {

    private var isLoadingMutableEvent = MutableLiveData<Boolean>()
    val isLoadingEvent: LiveData<Boolean>
        get() = isLoadingMutableEvent

    private var charactersMutableEvent = MutableLiveData<List<CharacterUI>>()
    val charactersEvent: LiveData<List<CharacterUI>>
        get() = charactersMutableEvent

    init {
        loadCharacters()
    }

    private fun loadCharacters() {
        viewModelScope.launch {
            isLoadingMutableEvent.value = true
            withContext(coroutinesDispatchers.io) {
                getCharactersUseCase().fold(
                    onSuccess = {
                        val charactersUI = it.toCharactersUI(stringConverter)
                        charactersMutableEvent.postValue(charactersUI)
                    },
                    onError = {
                        // TODO Handle error, create a message creator, interface here and implementation in the ui
                    }
                )
            }
            isLoadingMutableEvent.value = false
        }
    }
}