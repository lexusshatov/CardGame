package com.mauz.narutogame.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseAdapter<T, VB : ViewBinding>(
    private val onClick: (T) -> Unit = {},
) : ListAdapter<T, BaseAdapter<T, VB>.ViewHolder>(DefaultItemDiffCallback<T>()) {

    abstract val viewBindingProvider: (LayoutInflater, ViewGroup) -> VB
    abstract fun bind(item: T, binding: VB)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = viewBindingProvider(LayoutInflater.from(parent.context), parent)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class ViewHolder(private val binding: VB) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: T) {
            bind(item, binding)
            itemView.setOnClickListener { onClick(item) }
        }
    }
}