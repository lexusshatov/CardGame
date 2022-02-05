package com.mauz.narutogame.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mauz.narutogame.data.cash.UserCash

@Database(entities = [UserCash::class], version = 2)
@TypeConverters(Converter::class)
abstract class NarutoDatabase : RoomDatabase() {
    abstract fun narutoDao(): NarutoDao
}