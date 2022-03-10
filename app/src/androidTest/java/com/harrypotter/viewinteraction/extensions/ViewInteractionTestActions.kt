package com.harrypotter.viewinteraction.extensions

import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import com.harrypotter.features.characters.ui.adapter.CharactersViewHolder
import com.harrypotter.viewinteraction.ViewInteractionTest

fun ViewInteractionTest.performItemClickAtPosition(position: Int) {
    viewInteraction.perform(
        RecyclerViewActions.actionOnItemAtPosition<CharactersViewHolder>(
            position,
            ViewActions.click()
        )
    )
}