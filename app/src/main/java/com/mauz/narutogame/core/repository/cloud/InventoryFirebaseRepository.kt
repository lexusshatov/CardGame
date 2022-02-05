package com.mauz.narutogame.core.repository.cloud

import com.mauz.narutogame.core.repository.InventoryRepository
import com.mauz.narutogame.core.repository.UserRepository
import com.mauz.narutogame.data.Item
import javax.inject.Inject

class InventoryFirebaseRepository @Inject constructor(
    private val userRepository: UserRepository
): InventoryRepository {

    override fun getInventory(): List<Item> {
        TODO("Not yet implemented")
    }

    override fun addItem(item: Item) {
        TODO("Not yet implemented")
    }

    override fun removeItem(item: Item) {
        TODO("Not yet implemented")
    }
}