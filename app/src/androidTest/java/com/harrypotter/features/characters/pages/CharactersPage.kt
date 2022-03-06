package com.harrypotter.features.characters.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.harrypotter.R
import com.harrypotter.features.characters.ui.adapter.CharactersViewHolder
import org.hamcrest.core.IsNot.not

class CharactersPage {

    private val charactersRecyclerView = onView(withId(R.id.charactersRecyclerView))
    private val loadingView = onView(withId(R.id.loadingView))

    fun isPageDisplayed() {
        charactersRecyclerView.check(matches(isDisplayed()))
    }

    fun loadingIsVisible() {
        loadingView.check(matches(isDisplayed()))
    }

    fun loadingIsInvisible() {
        loadingView.check(matches(not(isDisplayed())))
    }

    fun clickItem(position: Int) {
        charactersRecyclerView.perform(
            RecyclerViewActions.actionOnItemAtPosition<CharactersViewHolder>(
                position,
                click()
            )
        )
    }

    fun checkExpectedDataIsShowed(position: Int) {
        // TODO Under dev
        // https://stackoverflow.com/questions/31394569/how-to-assert-inside-a-recyclerview-in-espresso
    }
}