package com.mouse.cardgame.core

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mouse.cardgame.data.UserLevel
import com.mouse.cardgame.data.User

@Database(entities = [User::class, UserLevel::class], version = 1)
abstract class NarutoDatabase : RoomDatabase() {
    abstract fun narutoDao(): NarutoDao
}