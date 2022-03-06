package com.harrypotter.features.characters.core.exceptions

import java.lang.Exception

data class NetworkException (val messageEx: String = ""): Exception()