package com.mauz.narutogame.data

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class User(
    val name: String,
    val photo: Uri? = null,
    val lvl: Int,
)