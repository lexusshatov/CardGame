package com.mauz.narutogame.core

sealed class LoginState {
    object Success : LoginState()
    object UserNotRegistered : LoginState()
}