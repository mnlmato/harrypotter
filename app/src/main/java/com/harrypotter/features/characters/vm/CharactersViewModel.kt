package com.harrypotter.features.characters.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harrypotter.features.characters.domain.usecase.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    fun showLog() {
        // TODO Inject interface with the dispatchers
        viewModelScope.launch {
            getCharactersUseCase().fold(
                onSuccess = {
                    Log.i("durcal", "result success, $it")
                },
                onError = {
                    Log.i("durcal", "an error happened, $it")
                }
            )
        }
    }
}