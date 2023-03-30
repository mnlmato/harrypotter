package com.harrypotter.coreui.resourceprovider

import android.content.Context
import androidx.annotation.StringRes
import javax.inject.Inject

class ResourceProvider @Inject constructor(private val context: Context) {
    fun getString(@StringRes resId: Int): String {
        return context.getString(resId)
    }
}