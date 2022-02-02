package com.mouse.cardgame.core.repository

interface UserRepository {
    fun getUsername(): String
    fun setUsername(username: String)
}