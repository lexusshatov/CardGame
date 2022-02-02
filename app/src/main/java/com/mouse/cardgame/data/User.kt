package com.mouse.cardgame.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey
    @ColumnInfo(name = "uid") val uid: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "lvl") val lvl: Int,
    @ColumnInfo(name = "experience") val experience: Long,
)