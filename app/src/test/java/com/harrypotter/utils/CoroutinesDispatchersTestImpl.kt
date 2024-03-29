package com.harrypotter.utils

import com.harrypotter.coreui.dispatchers.CoroutinesDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.TestDispatcher

class CoroutinesDispatchersTestImpl(
    testCoroutineDispatcher: TestDispatcher,
) : CoroutinesDispatchers {
    override val immediate: CoroutineDispatcher = testCoroutineDispatcher
    override val main: CoroutineDispatcher = testCoroutineDispatcher
    override val io: CoroutineDispatcher = testCoroutineDispatcher
}