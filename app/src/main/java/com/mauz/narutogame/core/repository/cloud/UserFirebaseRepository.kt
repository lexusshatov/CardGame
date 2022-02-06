package com.mauz.narutogame.core.repository.cloud

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mauz.narutogame.core.LoginState
import com.mauz.narutogame.core.RegisterState
import com.mauz.narutogame.core.data.User
import com.mauz.narutogame.core.data.cloud.UserCloud
import com.mauz.narutogame.core.repository.UserRepository
import com.mauz.narutogame.util.getAll
import com.mauz.narutogame.util.getResult
import com.mauz.narutogame.util.setResult
import com.mauz.narutogame.util.toFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserFirebaseRepository @Inject constructor() : UserRepository {

    private val firebaseUser = Firebase.auth.currentUser!!

    override suspend fun register(name: String): RegisterState {
        val isNameExist = Firebase.firestore.collection("users")
            .whereEqualTo("name", name)
            .getAll<UserCloud>()
            .any { it.name == name }
        return if (isNameExist) RegisterState.NameAlreadyExist
        else {
            val initUser = UserCloud(
                lvl = 1,
                name = name
            )
            Firebase.firestore.collection("users")
                .document(firebaseUser.uid)
                .setResult(initUser)

            RegisterState.Success
        }
    }

    override suspend fun login(): LoginState {
        var isUserExist = true
        runCatching {
            Firebase.firestore.collection("users")
                .document(firebaseUser.uid)
                .getResult<UserCloud>()
        }.getOrElse { isUserExist = false }
        return if (isUserExist) LoginState.Success
        else LoginState.UserNotRegistered
    }

    override suspend fun logout() {
        TODO("Not yet implemented")
    }

    override fun getUser(): Flow<User> {
        return Firebase.firestore.collection("users")
            .document(firebaseUser.uid)
            .toFlow<UserCloud>()
            .map { userCloud ->
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
}