package com.mauz.narutogame.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mauz.narutogame.data.User

@Database(entities = [User::class], version = 2)
@TypeConverters(Converter::class)
abstract class NarutoDatabase : RoomDatabase() {
    abstract fun narutoDao(): NarutoDao
}