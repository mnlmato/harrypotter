package com.harrypotter.utils

import com.harrypotter.coreui.dispatchers.CoroutinesDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher

@ExperimentalCoroutinesApi
class CoroutinesDispatchersTestImpl constructor(
    testCoroutineDispatcher: TestCoroutineDispatcher
) : CoroutinesDispatchers {
    override val immediate: CoroutineDispatcher = testCoroutineDispatcher
    override val main: CoroutineDispatcher = testCoroutineDispatcher
    override val io: CoroutineDispatcher = testCoroutineDispatcher
}