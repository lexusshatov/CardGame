package com.mauz.narutogame.core.repository

import com.mauz.narutogame.data.User


interface UserRepository {
    fun getUser(): User
    fun setUser(user: User)
}