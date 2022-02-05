package com.mauz.narutogame.di

import android.content.Context
import androidx.room.Room
import com.mauz.narutogame.core.db.NarutoDao
import com.mauz.narutogame.core.db.NarutoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): NarutoDatabase {
        return Room.databaseBuilder(
            context,
            NarutoDatabase::class.java,
            "NarutoDatabase"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNarutoDao(narutoDatabase: NarutoDatabase): NarutoDao {
        return narutoDatabase.narutoDao()
    }
}