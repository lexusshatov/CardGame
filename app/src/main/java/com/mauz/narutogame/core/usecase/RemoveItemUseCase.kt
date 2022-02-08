package com.mauz.narutogame.core.usecase

import com.mauz.narutogame.core.data.Item
import com.mauz.narutogame.core.repository.InventoryRepository
import javax.inject.Inject

class RemoveItemUseCase @Inject constructor(
    private val inventoryRepository: InventoryRepository,
) : UseCase<Unit, RemoveItemUseCase.Params>() {

    override suspend fun run(params: Params) {
        inventoryRepository.removeItem(params.item.id, params.count)
    }

    data class Params(
        val item: Item,
        val count: Int = 1,
    )
}