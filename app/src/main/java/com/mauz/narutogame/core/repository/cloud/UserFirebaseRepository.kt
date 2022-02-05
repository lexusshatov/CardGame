package com.mauz.narutogame.core.repository.cloud

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mauz.narutogame.core.repository.UserRepository
import com.mauz.narutogame.data.User
import com.mauz.narutogame.data.cloud.UserCloud
import com.mauz.narutogame.util.getResult
import com.mauz.narutogame.util.setResult
import com.mauz.narutogame.util.toFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class UserFirebaseRepository @Inject constructor() : UserRepository {

    private val firebaseUser
        get() = Firebase.auth.currentUser!!

    override suspend fun login() {
        if (!isExist()) {
            val initUser = UserCloud(
                lvl = 1,
                name = "username"
            )
            Firebase.firestore.collection("users")
                .document(firebaseUser.uid)
                .setResult(initUser)
        }
    }

    override suspend fun logout() {
        TODO("Not yet implemented")
    }

    override fun getUser(): Flow<User> {
        return Firebase.firestore.collection("users")
            .document(firebaseUser.uid)
            .toFlow<UserCloud>()
            .map { userCloud ->
                println(userCloud)
                User(
                    name = userCloud.name,
                    photo = firebaseUser.photoUrl,
                    lvl = userCloud.lvl
                )
            }
    }

    override suspend fun setUser(user: User) {
        TODO("Not yet implemented")
    }

    private suspend fun isExist(): Boolean {
        runCatching {
            Firebase.firestore.collection("users")
                .document(firebaseUser.uid)
                .getResult<UserCloud>()
        }.getOrElse { return false }
        return true
    }
}