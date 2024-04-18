package com.harrypotter.coreui.di

import com.harrypotter.coreui.vm.ClickThrottler
import com.harrypotter.coreui.vm.CustomThrottler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {

    @Provides
    fun provideClickThrottler(): CustomThrottler {
        return ClickThrottler()
    }
}