package com.mauz.narutogame.core.db

import android.net.Uri
import androidx.room.TypeConverter

class Converter {
    @TypeConverter
    fun toUri(uriString: String): Uri = Uri.parse(uriString)

    @TypeConverter
    fun fromUri(uri: Uri): String = uri.toString()
}