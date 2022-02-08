package com.mauz.narutogame.core.repository.cloud

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mauz.narutogame.core.data.Item
import com.mauz.narutogame.core.data.cloud.ItemCloud
import com.mauz.narutogame.core.data.cloud.ItemInfoCloud
import com.mauz.narutogame.core.repository.InventoryRepository
import com.mauz.narutogame.util.getResult
import com.mauz.narutogame.util.now
import com.mauz.narutogame.util.setResult
import com.mauz.narutogame.util.toFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class InventoryFirebaseRepository @Inject constructor() : InventoryRepository {

    private val firebaseUser: FirebaseUser
        get() = Firebase.auth.currentUser!!
    private val inventory: CollectionReference
        get() = Firebase.firestore.collection("users")
            .document(firebaseUser.uid)
            .collection("inventory")

    override fun getInventory(): Flow<List<Item>> {
        return inventory.toFlow<ItemCloud>()
            .map { items ->
                items.map {
                    val itemInfo = Firebase.firestore.collection("items")
                        .document(it.id)
                        .getResult<ItemInfoCloud>()
                    Item(id = it.id, count = it.count, icon = itemInfo.icon)
                }
            }
    }

    override suspend fun addItem(id: String, count: Int) {
        val itemReference = inventory.document(id)
        val item = runCatching {
            val foundItem = itemReference.getResult<ItemCloud>()
            ItemCloud(id = id, count = foundItem.count + count)
        }.getOrElse { ItemCloud(id = id, count = count) }
        itemReference.setResult(item)
    }

    override suspend fun removeItem(id: String, count: Int) {
        val itemReference = inventory.document(id)
        runCatching {
            val foundItem = itemReference.getResult<ItemCloud>()
            if (foundItem.count == count) {
                itemReference.delete()
            } else {
                itemReference.update("count", foundItem.count - count)
            }.now()
        }.getOrThrow()
    }
}