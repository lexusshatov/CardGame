package com.mauz.narutogame.core.repository

import com.mauz.narutogame.core.data.Item
import kotlinx.coroutines.flow.Flow

interface InventoryRepository {
    fun getInventory(): Flow<List<Item>>
    suspend fun addItem(id: String, count: Int = 1)
    suspend fun removeItem(item: Item)
}