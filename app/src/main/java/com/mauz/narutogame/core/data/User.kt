package com.mauz.narutogame.core.data

import android.net.Uri

data class User(
    val name: String,
    val photo: Uri? = null,
    val lvl: Int,
)