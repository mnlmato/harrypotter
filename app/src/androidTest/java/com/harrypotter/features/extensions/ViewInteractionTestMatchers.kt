package com.harrypotter.features.extensions

import android.view.View
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.util.HumanReadables
import com.harrypotter.features.ViewInteractionTest
import org.hamcrest.Matcher
import org.hamcrest.Matchers.any
import org.hamcrest.core.IsNot.not
import java.util.concurrent.TimeoutException

fun ViewInteractionTest.isDisplayed() {
    viewInteraction.check(matches(ViewMatchers.isDisplayed()))
}

fun ViewInteractionTest.isNotDisplayed() {
    viewInteraction.check(matches(not(ViewMatchers.isDisplayed())))
}

fun ViewInteractionTest.waitUntilIsVisible(timeout: Long = 500L) {
    viewInteraction.perform(waitUntilViewIsVisible(timeout))
}

private fun waitUntilViewIsVisible(timeout: Long) = object : ViewAction {
    override fun getConstraints(): Matcher<View> = any(View::class.java)

    override fun getDescription() =
        "wait up to $timeout milliseconds for the view to become visible"

    override fun perform(uiController: UiController?, view: View?) {
        val endTime = System.currentTimeMillis() + timeout

        do {
            if (view?.visibility == View.VISIBLE) return
            uiController?.loopMainThreadForAtLeast(50)
        } while (System.currentTimeMillis() < endTime)

        throw PerformException.Builder()
            .withActionDescription(description)
            .withCause(TimeoutException("Waited $timeout milliseconds"))
            .withViewDescription(HumanReadables.describe(view))
            .build()
    }
}