package com.harrypotter.coreui.stringconverter

import android.content.Context
import androidx.annotation.StringRes
import javax.inject.Inject

class StringConverterImpl @Inject constructor(
    private val context: Context
) : StringConverter {

    override fun convert(@StringRes id: Int) = context.getString(id)
}