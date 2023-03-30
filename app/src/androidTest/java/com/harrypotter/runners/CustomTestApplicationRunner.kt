package com.harrypotter.runners

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import dagger.hilt.android.testing.HiltTestApplication

class CustomTestApplicationRunner : AndroidJUnitRunner() {

    override fun newApplication(
        cl: ClassLoader?,
        appName: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, HiltTestApplication::class.java.name, context)
    }
}
