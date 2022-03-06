package com.harrypotter.features.extensions

import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import com.harrypotter.features.ViewInteractionTest
import org.hamcrest.core.IsNot.not

fun ViewInteractionTest.isDisplayed() {
    viewInteraction.check(matches(ViewMatchers.isDisplayed()))
}

fun ViewInteractionTest.isNotDisplayed() {
    viewInteraction.check(matches(not(ViewMatchers.isDisplayed())))
}