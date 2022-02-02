package com.mouse.cardgame.di

import com.mouse.cardgame.core.UserPreferences
import com.mouse.cardgame.core.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface BindsModule {

    @Binds
    fun bindUserRepository(userPreferences: UserPreferences): UserRepository
}