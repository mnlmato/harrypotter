package com.harrypotter.coredata

import android.content.Context
import javax.inject.Inject

class JsonReader @Inject constructor(
    private val context: Context,
) {
    fun read(jsonName: String): String {
        return context.assets.open(jsonName).bufferedReader().use { it.readText() }
    }
}