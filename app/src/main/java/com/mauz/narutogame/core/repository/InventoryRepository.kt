package com.mauz.narutogame.core.repository

import com.mauz.narutogame.data.Item

interface InventoryRepository {
    fun getInventory() : List<Item>
    fun addItem(item: Item)
    fun removeItem(item: Item)
}