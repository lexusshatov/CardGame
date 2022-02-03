package com.mauz.narutogame.util

import android.content.res.Resources

val Int.dp: Float
    get() = Resources.getSystem().displayMetrics.density * this