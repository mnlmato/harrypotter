package com.harrypotter.coreui.di

import android.content.Context
import com.harrypotter.coreui.stringconverter.StringConverter
import com.harrypotter.coreui.stringconverter.StringConverterImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class StringConverterModule {

    @Provides
    fun provideStringConverter(@ApplicationContext context: Context): StringConverter {
        return StringConverterImpl(context)
    }
}