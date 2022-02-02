package com.mouse.cardgame.core

interface UserRepository {
    fun getUsername(): String
    fun setUsername(username: String)
}