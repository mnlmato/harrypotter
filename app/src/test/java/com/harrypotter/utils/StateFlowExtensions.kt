package com.harrypotter.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope

suspend fun <T> Flow<T>.collectLastValue(): T {
    return this.take(1).first()
}

suspend fun <T> Flow<T>.collectTest(
    testScope: TestScope,
    takeValue: Int = 2,
    list: MutableList<T>,
) {
    testScope.launch {
        this@collectTest.take(takeValue).collect {
            list.add(it)
        }
    }
}