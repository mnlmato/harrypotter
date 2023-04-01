package com.harrypotter.features.characters.detail.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.harrypotter.designsystem.theme.CustomTheme
import com.harrypotter.features.characters.main.vm.model.CharacterUI
import com.harrypotter.features.characters.detail.ui.design.CharacterDetailScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailActivity : AppCompatActivity() {

    companion object {
        fun navigate(activity: Activity, character: CharacterUI) {
            val intent = Intent(activity, CharacterDetailActivity::class.java).apply {
                putExtra(ARG_CHARACTER, character)
            }
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomTheme {
                CharacterDetailScreen(
                    characterUI = getCharacter(),
                    onBackButtonClicked = { finish() }
                )
            }
        }
    }

    private fun getCharacter() = intent.extras?.getSerializable(ARG_CHARACTER) as? CharacterUI
        ?: throw IllegalArgumentException("CharacterUI is mandatory")
}

private const val ARG_CHARACTER = "ARG_CHARACTER"