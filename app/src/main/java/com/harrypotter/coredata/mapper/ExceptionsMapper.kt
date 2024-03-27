package com.harrypotter.coredata.mapper

import com.harrypotter.coreapp.exceptions.GenericException
import com.harrypotter.coreapp.exceptions.NetworkException
import com.harrypotter.coreapp.exceptions.UnavailableServerException
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection
import java.net.UnknownHostException

fun Exception.toCustomException(): Exception {
    return when (this) {
        is UnknownHostException -> this.toNetworkException()
        is IOException -> this.toNetworkException()
        is HttpException -> {
            if (this.code() == HttpURLConnection.HTTP_UNAVAILABLE) {
                UnavailableServerException(this.message.orEmpty())
            } else {
                this.toGenericException()
            }
        }

        else -> this.toGenericException()
    }
}

private fun IOException.toNetworkException() = NetworkException(this.message.orEmpty())
private fun UnknownHostException.toNetworkException() = NetworkException(this.message.orEmpty())
private fun Exception.toGenericException() = GenericException(this.message.orEmpty())
