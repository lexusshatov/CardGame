package com.mauz.narutogame.core.repository

import com.mauz.narutogame.core.LoginState
import com.mauz.narutogame.core.RegisterState
import com.mauz.narutogame.core.data.User
import kotlinx.coroutines.flow.Flow


interface UserRepository {
    suspend fun register(name: String): RegisterState
    suspend fun login(): LoginState
    suspend fun logout()

    fun getUser(): Flow<User>
    suspend fun setUser(user: User)
}