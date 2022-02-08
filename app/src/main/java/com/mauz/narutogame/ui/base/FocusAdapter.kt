package com.mauz.narutogame.ui.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class FocusAdapter<T, VB : ViewBinding> : BaseAdapter<T, VB>() {

    private var focusedPosition = RecyclerView.NO_POSITION
    val focus: T?
        get() = currentList.getOrNull(focusedPosition)

    open fun bindFocused(item: T, binding: VB) = bind(item, binding)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = currentList[position]
        if (position == focusedPosition) {
            bindFocused(item, holder.binding)
        } else {
            bind(item, holder.binding)
        }
        holder.itemView.setOnClickListener {
            val itemPosition = currentList.indexOf(item)
            val oldPosition = focusedPosition
            focusedPosition = itemPosition
            notifyItemChanged(oldPosition)
            notifyItemChanged(itemPosition)
            onClick(item)
        }
    }
}