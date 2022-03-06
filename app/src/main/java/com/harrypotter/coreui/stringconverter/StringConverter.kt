package com.harrypotter.coreui.stringconverter

import androidx.annotation.StringRes

interface StringConverter {
    fun convert(@StringRes id: Int): String
}