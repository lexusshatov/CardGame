package com.mauz.narutogame.di

import com.mauz.narutogame.core.repository.UserPreferences
import com.mauz.narutogame.core.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Suppress("unused")

@Module
@InstallIn(SingletonComponent::class)
interface BindsModule {

    @Binds
    fun bindUserRepository(userPreferences: UserPreferences): UserRepository
}