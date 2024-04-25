package com.harrypotter.coreapp

import java.lang.Exception

sealed class DataResult<out T> {

    data class Success<T>(val data: T) : DataResult<T>()
    data class Error(val ex: Exception) : DataResult<Nothing>()

    suspend fun <R> fold(
        onSuccess: suspend (data: T) -> R,
        onError: suspend (error: Exception) -> R,
    ): R {
        return if (this is Success) {
            onSuccess(this.data)
        } else {
            val exception = (this as Error).ex
            onError(exception)
        }
    }
}