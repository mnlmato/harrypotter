package com.harrypotter.features.characters.detail.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.harrypotter.coreui.ui.launchOnState
import com.harrypotter.coreui.vm.collect
import com.harrypotter.designsystem.theme.CustomTheme
import com.harrypotter.features.characters.detail.ui.design.CharacterDetailScreen
import com.harrypotter.features.characters.detail.vm.CharacterDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class CharacterDetailActivity : AppCompatActivity() {

    companion object {
        fun navigate(context: Context, characterId: String) {
            val intent = Intent(context, CharacterDetailActivity::class.java).apply {
                putExtra(ARG_CHARACTER, characterId)
            }
            context.startActivity(intent)
        }
    }

    private val viewModel: CharacterDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView()
        viewModel.loadCharacter(getCharacterId())
        subscribeToEvents()
    }

    private fun setContentView() {
        setContent {
            CustomTheme {
                CharacterDetailScreen(
                    characterUI = viewModel.characterEvent.collect(),
                    onBackButtonClicked = { viewModel.onBackClicked() },
                )
            }
        }
    }

    private fun getCharacterId() = intent.extras?.getString(ARG_CHARACTER)
        ?: throw IllegalArgumentException("Character id is mandatory")

    private fun subscribeToEvents() {
        launchOnState {
            viewModel.closeScreenEvent.collectLatest { finish() }
        }
    }
}

private const val ARG_CHARACTER = "ARG_CHARACTER"