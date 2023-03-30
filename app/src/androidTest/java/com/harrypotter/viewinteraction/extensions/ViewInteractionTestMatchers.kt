package com.harrypotter.viewinteraction.extensions

import androidx.annotation.StringRes
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.harrypotter.viewinteraction.ViewInteractionTest
import org.hamcrest.Matchers.not

fun ViewInteractionTest.isDisplayed() {
    viewInteraction.check(matches(ViewMatchers.isDisplayed()))
}

fun ViewInteractionTest.isNotDisplayed() {
    viewInteraction.check(matches(not(ViewMatchers.isDisplayed())))
}

fun ViewInteractionTest.isTextMatching(expectedResult: String) {
    viewInteraction.check(matches(withText(expectedResult)))
}

fun ViewInteractionTest.isTextMatching(@StringRes expectedResultResId: Int) {
    viewInteraction.check(matches(withText(expectedResultResId)))
}