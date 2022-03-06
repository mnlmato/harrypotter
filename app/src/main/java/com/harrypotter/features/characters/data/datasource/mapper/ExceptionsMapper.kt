package com.harrypotter.features.characters.data.datasource.mapper

import com.harrypotter.features.characters.core.exceptions.GenericException
import com.harrypotter.features.characters.core.exceptions.NetworkException
import java.io.IOException
import java.net.UnknownHostException

internal fun handleNetworkExceptions(ex: Exception): Exception {
    return when (ex) {
        is IOException -> ex.toNetworkException()
        is UnknownHostException -> ex.toNetworkException()
        else -> ex.toGenericException()
    }
}

private fun IOException.toNetworkException() = NetworkException(this.message ?: "")
private fun UnknownHostException.toNetworkException() = NetworkException(this.message ?: "")
private fun Exception.toGenericException() = GenericException(this.message ?: "")