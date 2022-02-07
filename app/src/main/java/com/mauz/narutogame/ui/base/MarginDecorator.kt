package com.mauz.narutogame.ui.base

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MarginDecorator(
    private val left: Float = 0f,
    private val right: Float = 0f,
    private val top: Float = 0f,
    private val bottom: Float = 0f,
) : RecyclerView.ItemDecoration() {

    constructor(all: Float) : this(all, all, all, all)
    constructor(horizontal: Float = 0f, vertical: Float = 0f) : this(
        horizontal,
        horizontal,
        vertical,
        vertical)

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        with(outRect) {
            left = this@MarginDecorator.left.toInt()
            right = this@MarginDecorator.right.toInt()
            top = this@MarginDecorator.top.toInt()
            bottom = this@MarginDecorator.bottom.toInt()
        }
    }
}