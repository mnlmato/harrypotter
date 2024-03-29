package com.harrypotter.features.characters.detail.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.harrypotter.coreui.vm.collect
import com.harrypotter.designsystem.theme.CustomTheme
import com.harrypotter.features.characters.detail.ui.design.CharacterDetailScreen
import com.harrypotter.features.characters.detail.vm.CharacterDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailActivity : AppCompatActivity() {

    companion object {
        fun navigate(activity: Activity, characterId: String) {
            val intent = Intent(activity, CharacterDetailActivity::class.java).apply {
                putExtra(ARG_CHARACTER, characterId)
            }
            activity.startActivity(intent)
        }
    }

    private val viewModel: CharacterDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView()
        observeCloseEvent()
        viewModel.loadCharacter(getCharacterId())
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

    private fun observeCloseEvent() {
        viewModel.closeScreenEvent.observe(this) { finish() }
    }

    private fun getCharacterId() = intent.extras?.getString(ARG_CHARACTER)
        ?: throw IllegalArgumentException("Character id is mandatory")
}

private const val ARG_CHARACTER = "ARG_CHARACTER"