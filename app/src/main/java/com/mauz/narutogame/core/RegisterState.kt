package com.mauz.narutogame.core

sealed class RegisterState {
    object Success : RegisterState()
    object NameAlreadyExist : RegisterState()
}
