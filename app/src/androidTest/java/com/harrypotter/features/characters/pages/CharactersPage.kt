package com.harrypotter.features.characters.pages

import com.harrypotter.R
import com.harrypotter.features.characters.ui.adapter.CharactersViewHolder
import com.harrypotter.viewinteraction.ViewInteractionTest
import com.harrypotter.viewinteraction.extensions.performItemClickAtPosition

class CharactersPage {

    val mainPageView = ViewInteractionTest(R.id.charactersMainView)
    val charactersList = ViewInteractionTest(R.id.charactersRecyclerView)
    val loadingView = ViewInteractionTest(R.id.loadingView)

    fun clickItem(position: Int) {
        charactersList.performItemClickAtPosition<CharactersViewHolder>(position)
    }
}