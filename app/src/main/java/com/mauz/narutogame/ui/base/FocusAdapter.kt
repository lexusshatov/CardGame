package com.mauz.narutogame.ui.base

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class FocusAdapter<T, VB : ViewBinding> : BaseAdapter<T, VB>() {

    private var focusedPosition = RecyclerView.NO_POSITION
    val focus: T?
        get() = currentList.getOrNull(focusedPosition)

    open fun bindFocused(item: T, binding: VB) = bind(item, binding)

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        if (position == focusedPosition) {
            bindFocused(currentList[position], holder.binding)
        } else {
            bind(currentList[position], holder.binding)
        }
        holder.itemView.setOnClickListener {
            val oldPosition = focusedPosition
            focusedPosition = position
            notifyItemChanged(oldPosition)
            notifyItemChanged(position)
            onClick(currentList[position])
        }
    }
}