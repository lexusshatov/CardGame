package com.mauz.narutogame.ui.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mauz.narutogame.core.data.Item
import com.mauz.narutogame.core.repository.InventoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    inventoryRepository: InventoryRepository,
) : ViewModel() {

    val inventory: LiveData<List<Item>> = inventoryRepository.getInventory().asLiveData()
}