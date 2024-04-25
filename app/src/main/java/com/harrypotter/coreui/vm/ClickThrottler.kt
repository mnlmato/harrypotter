package com.harrypotter.coreui.vm

import kotlinx.coroutines.delay
import javax.inject.Inject

class ClickThrottler @Inject constructor() {

    private var lastClickTime = 0L
    private var delay = 333L

    suspend fun onClick(action: suspend () -> Unit) {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastClickTime >= delay) {
            action()
            lastClickTime = currentTime
            delay(delay)
        }
    }
}