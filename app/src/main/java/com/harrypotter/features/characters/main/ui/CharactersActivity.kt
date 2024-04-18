package com.harrypotter.features.characters.main.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.harrypotter.coreui.ui.launchOnState
import com.harrypotter.designsystem.theme.CustomTheme
import com.harrypotter.features.characters.detail.ui.CharacterDetailActivity
import com.harrypotter.features.characters.main.ui.design.CharactersScreen
import com.harrypotter.features.characters.main.vm.CharactersViewModel
import com.harrypotter.features.characters.main.vm.model.CharactersState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class CharactersActivity : AppCompatActivity() {

    private val viewModel: CharactersViewModel by viewModels()

    private val onRetryButtonClickListener = {
        viewModel.onRetryButtonClicked()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadCharacters()
        subscribeToEvents()
    }

    private fun subscribeToEvents() {
        launchOnState {
            viewModel.charactersEvent.collectLatest { state ->
                when (state) {
                    is CharactersState.UI -> setContent(state)
                    is CharactersState.Actions.NavigateDetail -> showDetail(state.characterId)
                }
            }
        }

    }

    private fun setContent(charactersState: CharactersState.UI) {
        setContent {
            CustomTheme {
                CharactersScreen(
                    charactersState = charactersState,
                    onCharacterItemListener = { viewModel.onItemClick(it) },
                    onRetryButtonClickListener = onRetryButtonClickListener,
                )
            }
        }
    }

    private fun showDetail(characterId: String) {
        CharacterDetailActivity.navigate(this, characterId)
    }
}