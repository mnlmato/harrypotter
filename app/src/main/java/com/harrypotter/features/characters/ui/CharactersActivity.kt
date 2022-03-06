package com.harrypotter.features.characters.ui

import android.os.Bundle
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.harrypotter.R
import com.harrypotter.coreui.imageloader.ImageLoader
import com.harrypotter.features.characters.ui.adapter.CharactersAdapter
import com.harrypotter.features.characters.vm.CharactersViewModel
import com.harrypotter.features.characters.vm.model.CharacterUI
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CharactersActivity : AppCompatActivity() {

    @Inject
    lateinit var imageLoader: ImageLoader

    private val viewModel: CharactersViewModel by viewModels()

    private val charactersAdapter: CharactersAdapter by lazy {
        CharactersAdapter(imageLoader) {
            // TODO Handle item click
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characters)
        initViews()
        subscribeToEvents()
    }

    private fun initViews() {
        findViewById<RecyclerView>(R.id.charactersRecyclerView).adapter = charactersAdapter
    }

    private fun subscribeToEvents() {
        with(viewModel) {
            isLoadingEvent.observe(this@CharactersActivity, ::showLoading)
            charactersEvent.observe(this@CharactersActivity, ::showCharacters)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        findViewById<ProgressBar>(R.id.loadingView).isVisible = isLoading
    }

    private fun showCharacters(characters: List<CharacterUI>) {
        charactersAdapter.update(characters)
    }
}
