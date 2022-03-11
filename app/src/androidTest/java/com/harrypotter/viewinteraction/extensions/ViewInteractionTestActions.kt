package com.harrypotter.viewinteraction.extensions

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import com.harrypotter.viewinteraction.ViewInteractionTest

fun <T: RecyclerView.ViewHolder>ViewInteractionTest.performItemClickAtPosition(position: Int) {
    viewInteraction.perform(
        RecyclerViewActions.actionOnItemAtPosition<T>(
            position,
            ViewActions.click()
        )
    )
}