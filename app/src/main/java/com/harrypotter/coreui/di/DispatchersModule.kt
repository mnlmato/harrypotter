package com.harrypotter.coreui.di

import com.harrypotter.coreui.dispatchers.CoroutinesDispatchers
import com.harrypotter.coreui.dispatchers.CoroutinesDispatchersImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DispatchersModule {

    @Provides
    fun provideDispatchers(): CoroutinesDispatchers {
        return CoroutinesDispatchersImpl()
    }
}