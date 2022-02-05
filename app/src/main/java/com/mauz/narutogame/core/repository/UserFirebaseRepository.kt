package com.mauz.narutogame.core.repository

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mauz.narutogame.data.User
import javax.inject.Inject

class UserFirebaseRepository @Inject constructor() : UserRepository {

    override fun getUser(): User {
        val firebaseUser = Firebase.auth.currentUser!!
        return User(firebaseUser.displayName!!, firebaseUser.photoUrl)
    }

    override fun setUser(user: User) {
        TODO("Not yet implemented")
    }
}