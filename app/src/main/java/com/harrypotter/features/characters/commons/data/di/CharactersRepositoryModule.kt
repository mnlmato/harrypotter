package com.harrypotter.features.characters.commons.data.di

import com.harrypotter.features.characters.commons.data.repository.CharactersRepositoryImpl
import com.harrypotter.features.characters.main.domain.CharactersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CharactersRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCharactersRepository(charactersRepository: CharactersRepositoryImpl): CharactersRepository
}
