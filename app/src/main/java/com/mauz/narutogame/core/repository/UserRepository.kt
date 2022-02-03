package com.mauz.narutogame.core.repository

interface UserRepository {
    fun getToken(): String
    fun setToken(token: String)
}