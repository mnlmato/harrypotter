package com.harrypotter.coreapp.exceptions

import java.lang.Exception

data class NetworkException(val messageEx: String) : Exception()