package com.mauz.narutogame.ui.character

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.mauz.narutogame.R
import com.mauz.narutogame.core.data.Item
import com.mauz.narutogame.databinding.InventoryItemBinding
import com.mauz.narutogame.ui.base.FocusAdapter

class InventoryAdapter : FocusAdapter<Item, InventoryItemBinding>() {

    override val viewBindingProvider: (LayoutInflater, ViewGroup) -> InventoryItemBinding =
        { inflater, container ->
            InventoryItemBinding.inflate(inflater, container, false)
        }

    override fun bind(item: Item, binding: InventoryItemBinding) {
        Glide.with(binding.icon)
            .load(item.icon)
            .into(binding.icon)
        binding.count.text = item.count.toString()
        binding.root.strokeColor = ContextCompat.getColor(binding.root.context, R.color.light_gray)
    }

    override fun bindFocused(item: Item, binding: InventoryItemBinding) {
        super.bindFocused(item, binding)
        binding.root.strokeColor = Color.YELLOW
    }
}