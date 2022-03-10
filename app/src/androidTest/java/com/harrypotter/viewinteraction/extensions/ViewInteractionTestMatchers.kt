package com.harrypotter.viewinteraction.extensions

import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import com.harrypotter.viewinteraction.ViewInteractionTest
import org.hamcrest.Matchers.not

fun ViewInteractionTest.isDisplayed() {
    viewInteraction.check(matches(ViewMatchers.isDisplayed()))
}

fun ViewInteractionTest.isNotDisplayed() {
    viewInteraction.check(matches(not(ViewMatchers.isDisplayed())))
}