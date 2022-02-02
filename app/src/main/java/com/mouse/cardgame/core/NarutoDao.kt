package com.mouse.cardgame.core

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mouse.cardgame.data.User

@Dao
interface NarutoDao {

    @Query("SELECT CASE WHEN EXISTS (SELECT * FROM User WHERE name = :name) THEN '1' ELSE '0' END")
    fun isExist(name: String): Boolean

    @Query("SELECT * FROM User WHERE name = :name")
    fun getByName(name: String): User

    @Query("SELECT UserLevel.expected_experience FROM UserLevel WHERE lvl = :lvl")
    suspend fun getExpectedExperience(lvl: Int): Long

    @Update
    suspend fun updateUser(user: User)

    @Insert
    suspend fun addUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)
}