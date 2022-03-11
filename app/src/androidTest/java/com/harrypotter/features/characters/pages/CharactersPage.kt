package com.harrypotter.features.characters.pages

import com.harrypotter.R
import com.harrypotter.features.characters.ui.adapter.CharactersViewHolder
import com.harrypotter.viewinteraction.ViewInteractionTest
import com.harrypotter.viewinteraction.extensions.isDisplayed
import com.harrypotter.viewinteraction.extensions.isNotDisplayed
import com.harrypotter.viewinteraction.extensions.performItemClickAtPosition

class CharactersPage {

    private val charactersRecyclerView = ViewInteractionTest(R.id.charactersRecyclerView)
    private val loadingView = ViewInteractionTest(R.id.loadingView)

    fun isPageDisplayed() {
        charactersRecyclerView.isDisplayed()
    }

    fun loadingIsInvisible() {
        loadingView.isNotDisplayed()
    }

    fun clickItem(position: Int) {
        charactersRecyclerView.performItemClickAtPosition<CharactersViewHolder>(position)
    }
}