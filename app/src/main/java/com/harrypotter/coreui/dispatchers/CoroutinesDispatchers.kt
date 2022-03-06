package com.harrypotter.coreui.dispatchers

import kotlinx.coroutines.CoroutineDispatcher

interface CoroutinesDispatchers {
    val immediate: CoroutineDispatcher
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
}
