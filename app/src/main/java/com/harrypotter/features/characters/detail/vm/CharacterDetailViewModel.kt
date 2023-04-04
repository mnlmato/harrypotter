package com.harrypotter.features.characters.detail.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.harrypotter.coreui.resourceprovider.ResourceProvider
import com.harrypotter.coreui.vm.SingleLiveData
import com.harrypotter.features.characters.detail.domain.usecase.GetCharacterUseCase
import com.harrypotter.features.characters.main.vm.mapper.toCharacterUI
import com.harrypotter.features.characters.main.vm.model.CharacterUI
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterUseCase: GetCharacterUseCase,
    private val resourceProvider: ResourceProvider,
) : ViewModel() {

    val closeScreenEvent = SingleLiveData<Unit>()

    private val characterMutableEvent = MutableLiveData<CharacterUI>()
    val characterEvent: LiveData<CharacterUI>
        get() = characterMutableEvent

    fun loadCharacter(id: String) {
        characterMutableEvent.value = getCharacterUseCase(id).toCharacterUI(resourceProvider)
    }

    fun onBackClicked() {
        closeScreenEvent.value = Unit
    }
}