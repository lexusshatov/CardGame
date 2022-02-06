package com.mauz.narutogame.core.repository

import com.mauz.narutogame.core.data.InventoryItem
import kotlinx.coroutines.flow.Flow

interface InventoryRepository {
    fun getInventory(): Flow<List<InventoryItem>>
    fun addItem(inventoryItem: InventoryItem)
    fun removeItem(inventoryItem: InventoryItem)
}