package com.harrypotter.features.characters.main.ui

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.harrypotter.coreui.vm.collect
import com.harrypotter.designsystem.theme.CustomTheme
import com.harrypotter.features.characters.detail.ui.CharacterDetailActivity
import com.harrypotter.features.characters.main.ui.design.CharactersScreen
import com.harrypotter.features.characters.main.vm.CharactersViewModel
import com.harrypotter.features.characters.main.vm.model.CharactersActionsFromUI
import com.harrypotter.features.characters.main.vm.model.CharactersState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersActivity : AppCompatActivity() {

    private val viewModel: CharactersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent()
        viewModel.loadCharacters()
        setListeners()
        subscribeToSingleEvents()
    }

    private fun setContent() {
        setContent {
            CustomTheme {
                CharactersScreen(
                    charactersState = viewModel.charactersStateEvent.collect(),
                    onClickAction = {
                        viewModel.onCLickAction(it)
                    },
                )
            }
        }
    }

    private fun setListeners() {
        onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    viewModel.onCLickAction(CharactersActionsFromUI.OnBackClick)
                }
            },
        )
    }

    private fun subscribeToSingleEvents() {
        viewModel.actionsSingleEvents.observe(this) { event ->
            when (event) {
                is CharactersState.SingleActions.ShowCharacterDetail -> {
                    CharacterDetailActivity.navigate(this, characterId = event.id)
                }

                is CharactersState.SingleActions.CloseScreen -> {
                    finish()
                }
            }
        }
    }
}