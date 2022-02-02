package com.mouse.cardgame.core

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

    override fun getUsername(): String {
        return preferences.getString(KEY_USERNAME, "")!!
    }

    override fun setUsername(username: String) {
        preferences.edit {
            putString(USER_PREFERENCES, username)
        }
    }


    private companion object {
        const val USER_PREFERENCES = "user_preferences"
        const val KEY_USERNAME = "username"
    }
}