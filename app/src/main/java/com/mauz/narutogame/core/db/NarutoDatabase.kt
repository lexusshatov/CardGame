package com.mauz.narutogame.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mauz.narutogame.data.UserLevel
import com.mauz.narutogame.data.User

@Database(entities = [User::class, UserLevel::class], version = 1)
abstract class NarutoDatabase : RoomDatabase() {
    abstract fun narutoDao(): NarutoDao
}