package com.mauz.narutogame.core.data.cash

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserCash(
    @PrimaryKey
    @ColumnInfo(name = "uid") val uid: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "photo_uri") val photo: Uri? = null,
    @ColumnInfo(name = "lvl") val lvl: Int,
    @ColumnInfo(name = "progress") val progress: Int
)