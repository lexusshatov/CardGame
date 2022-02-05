package com.mauz.narutogame.util

import com.google.firebase.firestore.DocumentReference
import com.mauz.narutogame.core.error.DocumentNotFoundException
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

suspend inline fun <reified T> DocumentReference.getResult(): T = suspendCoroutine { continuation ->
    get()
        .addOnCompleteListener {
            if (it.result.data == null) {
                continuation.resumeWithException(DocumentNotFoundException())
                return@addOnCompleteListener
            }
            val result = it.result.toObject(T::class.java)!!
            continuation.resume(result)
        }
        .addOnFailureListener { throw it }
}

suspend inline fun <reified T : Any> DocumentReference.setResult(document: T): Unit =
    suspendCoroutine { continuation ->
        set(document)
            .addOnCompleteListener {
                continuation.resume(Unit)
            }
            .addOnFailureListener { throw it }
    }

inline fun <reified T> DocumentReference.toFlow(): Flow<T> {
    val flow =
        MutableSharedFlow<T>(extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    this@toFlow.addSnapshotListener { snapshot, error ->
        if (error != null) throw error
        if (snapshot == null || !snapshot.exists()) throw DocumentNotFoundException()
        flow.tryEmit(snapshot.toObject(T::class.java)!!)
    }
    return flow
}