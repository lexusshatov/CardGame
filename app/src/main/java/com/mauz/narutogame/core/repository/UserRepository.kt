package com.mauz.narutogame.core.repository

interface UserRepository {
    fun getUsername(): String
    fun setUsername(username: String)
}