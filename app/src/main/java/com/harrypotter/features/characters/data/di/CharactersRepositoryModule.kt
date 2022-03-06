package com.harrypotter.features.characters.data.di

import com.harrypotter.features.characters.data.repository.CharactersRepositoryImpl
import com.harrypotter.features.characters.domain.CharactersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CharactersRepositoryModule {

    @Binds
    abstract fun bindCharactersRepository(charactersRepository: CharactersRepositoryImpl): CharactersRepository
}
