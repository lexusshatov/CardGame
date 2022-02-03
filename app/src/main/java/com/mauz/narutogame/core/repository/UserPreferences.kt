package com.mauz.narutogame.core.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class UserPreferences @Inject constructor(
    @ApplicationContext private val context: Context,
) : UserRepository {

    private val preferences: SharedPreferences by lazy {
        context.getSharedPreferences(USER_PREFERENCES, Context.MODE_PRIVATE)
    }

    override fun getToken(): String {
        return preferences.getString(KEY_TOKEN, "")!!
    }

    override fun setToken(token: String) {
        preferences.edit {
            putString(KEY_TOKEN, token)
        }
    }


    private companion object {
        const val USER_PREFERENCES = "user_preferences"
        const val KEY_TOKEN = "username"
    }
}