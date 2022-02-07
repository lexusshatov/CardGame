package com.mauz.narutogame.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mauz.narutogame.databinding.ButtonItemBinding
import com.mauz.narutogame.ui.base.BaseAdapter

class ButtonsAdapter : BaseAdapter<String, ButtonItemBinding>() {

    override val viewBindingProvider: (LayoutInflater, ViewGroup) -> ButtonItemBinding =
        { inflater, container ->
            ButtonItemBinding.inflate(inflater, container, false)
        }

    override fun bind(item: String, binding: ButtonItemBinding) {
        binding.button.text = item
    }
}