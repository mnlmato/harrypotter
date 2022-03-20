package com.harrypotter.features.characters

import androidx.test.espresso.IdlingResource
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.harrypotter.features.characters.ui.CharactersActivity
import java.util.concurrent.atomic.AtomicBoolean

fun CharactersScreenFlow.getLoadingInvisibleIdlingResource(
    scenarioRule: ActivityScenarioRule<CharactersActivity>
) = object : IdlingResource {

    private var isIdle = AtomicBoolean(false)
    private lateinit var resourceCallback: IdlingResource.ResourceCallback

    override fun getName(): String = this::class.java.toString()

    override fun isIdleNow(): Boolean {
        scenarioRule.scenario.onActivity {
            it.viewModel.showLoadingEvent.observe(it) { isVisible ->
                isIdle.set(!isVisible)
            }
        }

        if (isIdle.get()) resourceCallback.onTransitionToIdle()

        return isIdle.get()
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback) {
        resourceCallback = callback
    }
}