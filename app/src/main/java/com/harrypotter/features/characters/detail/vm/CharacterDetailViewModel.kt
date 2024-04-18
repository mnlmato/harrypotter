package com.harrypotter.features.characters.detail.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harrypotter.coreui.resourceprovider.ResourceProvider
import com.harrypotter.features.characters.detail.domain.usecase.GetCharacterUseCase
import com.harrypotter.features.characters.main.vm.mapper.toCharacterUI
import com.harrypotter.features.characters.main.vm.model.CharacterUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterUseCase: GetCharacterUseCase,
    private val resourceProvider: ResourceProvider,
) : ViewModel() {

    private val _closeScreenEvent = MutableSharedFlow<Unit>()
    val closeScreenEvent: SharedFlow<Unit> = _closeScreenEvent

    private val characterMutableEvent = MutableLiveData<CharacterUI>()
    val characterEvent: LiveData<CharacterUI>
        get() = characterMutableEvent

    fun loadCharacter(id: String) {
        characterMutableEvent.value = getCharacterUseCase(id).toCharacterUI(resourceProvider)
    }

    fun onBackClicked() {
        viewModelScope.launch {
            _closeScreenEvent.emit(Unit)
        }
    }
}