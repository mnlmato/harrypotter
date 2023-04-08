package com.harrypotter.coredata.mapper

import com.harrypotter.coreapp.exceptions.GenericException
import com.harrypotter.coreapp.exceptions.NetworkException
import java.io.IOException
import java.net.UnknownHostException

fun Exception.toCustomException(): Exception {
    return when (this) {
        is UnknownHostException -> this.toNetworkException()
        is IOException -> this.toNetworkException()
        else -> this.toGenericException()
    }
}

private fun IOException.toNetworkException() = NetworkException(this.message ?: "")
private fun UnknownHostException.toNetworkException() = NetworkException(this.message ?: "")
private fun Exception.toGenericException() = GenericException(this.message ?: "")