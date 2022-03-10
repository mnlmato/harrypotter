package com.harrypotter.coredata.di

import com.harrypotter.coredata.UrlProvider
import com.harrypotter.coredata.UrlProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UrlProviderModule {

    @Provides
    @Singleton
    fun provideUrlProvider(): UrlProvider {
        return UrlProviderImpl()
    }
}
