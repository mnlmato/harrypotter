package com.harrypotter.di

import com.harrypotter.coredata.UrlProvider
import com.harrypotter.testdependencies.UrlProviderTestImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UrlProviderTestModule {

    @Provides
    @Singleton
    fun provideUrlProvider(): UrlProvider {
        return UrlProviderTestImpl()
    }
}