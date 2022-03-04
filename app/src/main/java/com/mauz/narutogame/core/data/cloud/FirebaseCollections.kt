package com.mauz.narutogame.core.data.cloud

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object FirebaseCollections {

    val firebaseUser: FirebaseUser
        get() = Firebase.auth.currentUser!!
    val users: CollectionReference
        get() = Firebase.firestore.collection("users")
    val user: DocumentReference
        get() = users.document(firebaseUser.uid)
    val inventory: CollectionReference
        get() = user.collection("inventory")
}