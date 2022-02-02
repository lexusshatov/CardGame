package com.mouse.cardgame.core

import androidx.room.Dao
import androidx.room.Query
import com.mouse.cardgame.data.User

@Dao
interface NarutoDao {

    @Query("SELECT * FROM User WHERE uid = :uid")
    fun getById(uid: String): User

    @Query("SELECT UserLevel.expected_experience FROM UserLevel WHERE lvl = :lvl")
    fun getExpectedExperience(lvl: Int): Long
}