package com.mouse.cardgame.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "lvl") val lvl: Int = 1,
    @ColumnInfo(name = "experience") val experience: Long = 0,
)