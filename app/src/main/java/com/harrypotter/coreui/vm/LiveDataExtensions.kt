package com.harrypotter.coreui.vm

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData

/**
 *  Use to observe a LiveData and consume the value in a composable function
 * */
@Composable
fun <T> LiveData<T>.collect(): T =
    observeAsState().value ?: throw IllegalStateException("LiveData value should be initialized")