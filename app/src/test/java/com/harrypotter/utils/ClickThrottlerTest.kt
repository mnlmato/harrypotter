package com.harrypotter.utils

import com.harrypotter.coreui.vm.CustomThrottler

class ClickThrottlerTest : CustomThrottler {
    override suspend fun onClick(action: suspend () -> Unit) {
        println("fake click and action")
        action()
    }
}