package com.mauz.narutogame.core.repository.cash

import android.content.Context
import android.content.res.Resources
import com.mauz.narutogame.core.repository.ResourceProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ResourceProviderCash @Inject constructor(
    @ApplicationContext private val context: Context,
) : ResourceProvider {
    override fun getResources(): Resources = context.resources
}