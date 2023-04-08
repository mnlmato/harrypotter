package com.harrypotter.features.characters.main.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.harrypotter.coreui.vm.collect
import com.harrypotter.designsystem.theme.CustomTheme
import com.harrypotter.features.characters.detail.ui.CharacterDetailActivity
import com.harrypotter.features.characters.main.ui.design.CharactersScreen
import com.harrypotter.features.characters.main.ui.design.OnCharacterItemListener
import com.harrypotter.features.characters.main.vm.CharactersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersActivity : AppCompatActivity() {

    private val viewModel: CharactersViewModel by viewModels()

    private val onCharacterItemListener = OnCharacterItemListener {
        viewModel.onItemClick(it)
    }

    private val onRetryButtonClickListener = {
        viewModel.onRetryButtonClicked()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent()
        viewModel.loadCharacters()
        subscribeToShowDetailEvent()
    }

    private fun setContent() {
        setContent {
            CustomTheme {
                CharactersScreen(
                    charactersState = viewModel.charactersStateEvent.collect(),
                    onCharacterItemListener,
                    onRetryButtonClickListener,
                )
            }
        }
    }

    private fun subscribeToShowDetailEvent() {
        viewModel.showDetailEvent.observe(this@CharactersActivity, ::showDetail)
    }

    private fun showDetail(characterId: String) {
        CharacterDetailActivity.navigate(this, characterId)
    }
}