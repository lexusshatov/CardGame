package com.mauz.narutogame.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserLevel(
    @PrimaryKey
    @ColumnInfo(name = "lvl") val lvl: Int,
    @ColumnInfo(name = "expected_experience") val expectedExperience: Long,
)
