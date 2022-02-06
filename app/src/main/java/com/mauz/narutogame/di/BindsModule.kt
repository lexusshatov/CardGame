package com.mauz.narutogame.di

import com.mauz.narutogame.core.repository.InventoryRepository
import com.mauz.narutogame.core.repository.ResourceProvider
import com.mauz.narutogame.core.repository.UserRepository
import com.mauz.narutogame.core.repository.cash.ResourceProviderCash
import com.mauz.narutogame.core.repository.cloud.InventoryFirebaseRepository
import com.mauz.narutogame.core.repository.cloud.UserFirebaseRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Suppress("unused")

@Module
@InstallIn(SingletonComponent::class)
interface BindsModule {

    @Binds
    fun bindUserRepository(impl: UserFirebaseRepository): UserRepository

    @Binds
    fun bindResourceRepository(impl: ResourceProviderCash): ResourceProvider

    @Binds
    fun bindInventoryRepository(impl: InventoryFirebaseRepository): InventoryRepository
}