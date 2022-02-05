package com.mauz.narutogame.core.repository

import com.mauz.narutogame.data.User
import kotlinx.coroutines.flow.Flow


interface UserRepository {
    suspend fun login()
    suspend fun logout()
    fun getUser(): Flow<User>
    suspend fun setUser(user: User)
}