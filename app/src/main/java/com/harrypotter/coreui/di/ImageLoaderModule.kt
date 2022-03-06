package com.harrypotter.coreui.di

import com.harrypotter.coreui.imageloader.ImageLoader
import com.harrypotter.coreui.imageloader.ImageLoaderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class ImageLoaderModule {

    @Binds
    abstract fun provideImageLoader(imageLoaderImpl: ImageLoaderImpl): ImageLoader
}
