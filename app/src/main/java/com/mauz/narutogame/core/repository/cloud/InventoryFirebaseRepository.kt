package com.mauz.narutogame.core.repository.cloud

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mauz.narutogame.core.data.InventoryItem
import com.mauz.narutogame.core.data.cloud.InventoryItemCloud
import com.mauz.narutogame.core.data.cloud.ItemInfoCloud
import com.mauz.narutogame.core.repository.InventoryRepository
import com.mauz.narutogame.util.getResult
import com.mauz.narutogame.util.toFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class InventoryFirebaseRepository @Inject constructor() : InventoryRepository {

    private val firebaseUser = Firebase.auth.currentUser!!

    override fun getInventory(): Flow<List<InventoryItem>> {
        return Firebase.firestore.collection("users")
            .document(firebaseUser.uid)
            .collection("inventory")
            .toFlow<InventoryItemCloud>()
            .map { items ->
                println("SUCCESS ITEMS: $items")
                items.map {
                    val itemInfo = Firebase.firestore.collection("items")
                        .document(it.id)
                        .getResult<ItemInfoCloud>()
                    InventoryItem(
                        count = it.count,
                        icon = itemInfo.icon
                    )
                }
            }
    }

    override fun addItem(inventoryItem: InventoryItem) {
        TODO("Not yet implemented")
    }

    override fun removeItem(inventoryItem: InventoryItem) {
        TODO("Not yet implemented")
    }
}