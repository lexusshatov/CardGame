package com.mauz.narutogame.util

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

class DefaultItemDiffCallback<Item> : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    }
}