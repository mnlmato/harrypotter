package com.harrypotter.features.characters.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.harrypotter.R
import com.harrypotter.coreui.imageloader.ImageLoader
import com.harrypotter.features.characters.vm.model.CharacterUI
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

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

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail)
        populate()
    }

    private fun populate() {
        with(getCharacter()) {
            imageLoader.loadImage(findViewById(R.id.pictureDetailImageView), imageUrl)
            findViewById<TextView>(R.id.nameDetailTextView).text = name
            findViewById<TextView>(R.id.houseDetailTextView).text = getString(house)
            findViewById<TextView>(R.id.actorNameDetailTextView).text = actorName
            findViewById<TextView>(R.id.genderDetailTextView).text = getString(gender)
            findViewById<TextView>(R.id.speciesDetailTextView).text = getString(species)
            findViewById<TextView>(R.id.birthDetailTextView).text =
                birth.ifBlank { getString(R.string.birthday_unknown) }
        }
    }

    private fun getCharacter() = intent.extras?.getSerializable(ARG_CHARACTER) as? CharacterUI
        ?: throw IllegalArgumentException("CharacterUI is mandatory")
}

private const val ARG_CHARACTER = "ARG_CHARACTER"