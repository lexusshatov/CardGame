package com.mauz.narutogame.data

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "photo_uri") val photo: Uri? = null,
    @ColumnInfo(name = "lvl") val lvl: Int = 1,
)