package com.mauz.narutogame.util

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Query
import com.mauz.narutogame.core.error.DocumentNotFoundException
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

suspend inline fun <reified T> DocumentReference.getResult(): T = suspendCoroutine { continuation ->
    get()
        .addOnSuccessListener { document ->
            if (document.data == null) {
                continuation.resumeWithException(DocumentNotFoundException())
                return@addOnSuccessListener
            }
            val result = document.toObject(T::class.java)!!
            continuation.resume(result)
        }
        .addOnFailureListener { continuation.resumeWithException(it) }
}

suspend inline fun <reified T : Any> DocumentReference.setResult(document: T): Unit =
    suspendCoroutine { continuation ->
        set(document)
            .addOnCompleteListener { continuation.resume(Unit) }
            .addOnFailureListener { continuation.resumeWithException(it) }
    }

inline fun <reified T> DocumentReference.toFlow(): Flow<T> {
    val flow = MutableSharedFlow<T>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    addSnapshotListener { snapshot, error ->
        if (error != null) throw error
        if (snapshot == null || !snapshot.exists()) throw DocumentNotFoundException()
        flow.tryEmit(snapshot.toObject(T::class.java)!!)
    }
    return flow
}

inline fun <reified T : Any> CollectionReference.toFlow(): Flow<List<T>> {
    val flow = MutableSharedFlow<List<T>>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    addSnapshotListener { snapshot, error ->
        if (error != null) throw error
        if (snapshot == null) throw DocumentNotFoundException()
        val list = snapshot.documents.map {
            it.toObject(T::class.java)!!
        }
        flow.tryEmit(list)
    }
    return flow
}

suspend inline fun <reified T : Any> Query.getAll(): List<T> =
    suspendCoroutine { continuation ->
        get()
            .addOnSuccessListener { query ->
                val result = query.documents
                    .map { it.toObject(T::class.java)!! }
                continuation.resume(result)
            }
            .addOnFailureListener { continuation.resumeWithException(it) }
    }

suspend inline fun Task<Void>.now() = suspendCoroutine<Unit> { continuation ->
    addOnSuccessListener { continuation.resume(Unit) }
    addOnFailureListener { continuation.resumeWithException(it) }
}