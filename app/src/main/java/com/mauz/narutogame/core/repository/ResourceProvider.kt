package com.mauz.narutogame.core.repository

import android.content.Context
import android.content.res.Resources
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface ResourceProvider {
    fun getResources(): Resources

    class Base @Inject constructor(
        @ApplicationContext private val context: Context,
    ) : ResourceProvider {

        override fun getResources(): Resources = context.resources
    }
}