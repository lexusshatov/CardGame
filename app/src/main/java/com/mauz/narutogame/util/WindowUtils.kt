package com.mauz.narutogame.util

import android.view.Window
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

fun Window.hideSystemUI() {
    WindowCompat.setDecorFitsSystemWindows(this, false)
    WindowInsetsControllerCompat(this, decorView).let { controller ->
        controller.hide(WindowInsetsCompat.Type.systemBars())
        controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }
}

fun Window.showSystemUI() {
    WindowCompat.setDecorFitsSystemWindows(this, true)
    WindowInsetsControllerCompat(this, decorView).show(WindowInsetsCompat.Type.systemBars())
}