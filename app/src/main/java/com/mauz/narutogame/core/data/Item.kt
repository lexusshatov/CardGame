package com.mauz.narutogame.core.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item(
    val id: String,
    val count: Int,
    val icon: String,
) : Parcelable