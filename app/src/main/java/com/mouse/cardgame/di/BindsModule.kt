package com.mouse.cardgame.di

import com.mouse.cardgame.core.repository.UserPreferences
import com.mouse.cardgame.core.repository.UserRepository
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