package com.mauz.narutogame.ui.character

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.mauz.narutogame.core.data.InventoryItem
import com.mauz.narutogame.databinding.InventoryItemBinding
import com.mauz.narutogame.util.BaseAdapter

class InventoryAdapter(private val size: Int = 64) : BaseAdapter<InventoryItem, InventoryItemBinding>() {

    override val viewBindingProvider: (LayoutInflater, ViewGroup) -> InventoryItemBinding =
        { inflater, container ->
            InventoryItemBinding.inflate(inflater, container, false)
        }

    override fun bind(item: InventoryItem, binding: InventoryItemBinding) {
        Glide.with(binding.icon)
            .load(item.icon)
            .into(binding.icon)
        binding.count.text = item.count.toString()
    }
}