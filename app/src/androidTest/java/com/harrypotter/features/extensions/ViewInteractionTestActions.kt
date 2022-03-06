package com.harrypotter.features.extensions

import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import com.harrypotter.features.ViewInteractionTest
import com.harrypotter.features.characters.ui.adapter.CharactersViewHolder

fun ViewInteractionTest.performItemClickAtPosition(position: Int) {
    viewInteraction.perform(
        RecyclerViewActions.actionOnItemAtPosition<CharactersViewHolder>(
            position,
            ViewActions.click()
        )
    )
}