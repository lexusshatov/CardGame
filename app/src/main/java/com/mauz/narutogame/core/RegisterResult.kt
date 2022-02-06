package com.mauz.narutogame.core

sealed class RegisterResult {
    object Success : RegisterResult()
    object NameAlreadyExist : RegisterResult()
}
