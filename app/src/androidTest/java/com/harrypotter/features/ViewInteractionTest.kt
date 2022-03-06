package com.harrypotter.features

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.ViewMatchers.withId

class ViewInteractionTest(@IdRes val viewId: Int) {
    val viewInteraction: ViewInteraction = onView(withId(viewId))
}