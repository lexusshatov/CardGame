package com.mauz.narutogame.core

sealed class LoginResult {
    object Success : LoginResult()
    object UserNotRegistered : LoginResult()
}