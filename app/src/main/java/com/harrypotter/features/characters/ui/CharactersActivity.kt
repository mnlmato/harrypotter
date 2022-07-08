package com.harrypotter.features.characters.ui

import android.os.Bundle
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
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
            viewModel.onItemClick(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characters)

        viewModel.loadCharacters()
        initViews()
        setListeners()
        subscribeToEvents()
    }

    private fun initViews() {
        findViewById<RecyclerView>(R.id.charactersRecyclerView).apply {
            adapter = charactersAdapter
            addItemDecoration(
                DividerItemDecoration(this@CharactersActivity, DividerItemDecoration.VERTICAL)
            )
        }
    }

    private fun setListeners() {
        findViewById<MaterialButton>(R.id.genericErrorButton).setOnClickListener {
            viewModel.onRetryButtonClicked()
        }
    }

    private fun subscribeToEvents() {
        with(viewModel) {
            showLoadingEvent.observe(this@CharactersActivity, ::showLoading)
            showGenericErrorEvent.observe(this@CharactersActivity, ::showGenericError)
            charactersEvent.observe(this@CharactersActivity, ::showCharacters)
            showDetailEvent.observe(this@CharactersActivity, ::showDetail)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        findViewById<ProgressBar>(R.id.loadingView).isVisible = isLoading
    }

    private fun showGenericError(isErrorVisible: Boolean) {
        findViewById<ViewGroup>(R.id.genericErrorMainViewGroup).isVisible = isErrorVisible
    }

    private fun showCharacters(characters: List<CharacterUI>) {
        charactersAdapter.update(characters)
    }

    private fun showDetail(character: CharacterUI) {
        CharacterDetailActivity.navigate(this, character)
    }
}
