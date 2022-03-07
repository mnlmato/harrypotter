package com.harrypotter.coredata

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UrlProviderModule {

    // TODO Move this modules to another package and try to use the @Binds annotation
    @Provides
    @Singleton
    fun provideUrlProvider(): UrlProvider {
        return UrlProviderImpl()
    }
}
