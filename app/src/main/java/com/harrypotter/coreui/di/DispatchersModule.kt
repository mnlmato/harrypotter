package com.harrypotter.coreui.di

import android.content.Context
import com.harrypotter.coreui.dispatchers.CoroutinesDispatchers
import com.harrypotter.coreui.dispatchers.CoroutinesDispatchersImpl
import com.harrypotter.coreui.resourceprovider.ResourceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DispatchersModule {

    @Provides
    @Singleton
    fun provideDispatchers(): CoroutinesDispatchers {
        return CoroutinesDispatchersImpl()
    }

    @Provides
    @Singleton
    fun provideResourceProvider(@ApplicationContext context: Context): ResourceProvider {
        return ResourceProvider(context)
    }
}