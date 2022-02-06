package com.mauz.narutogame.core.repository

import com.mauz.narutogame.core.LoginResult
import com.mauz.narutogame.core.RegisterResult
import com.mauz.narutogame.data.User
import kotlinx.coroutines.flow.Flow


interface UserRepository {
    suspend fun register(name: String): RegisterResult
    suspend fun login(): LoginResult
    suspend fun logout()

    fun getUser(): Flow<User>
    suspend fun setUser(user: User)
}