package com.mauz.narutogame.ui.character

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mauz.narutogame.R
import com.mauz.narutogame.databinding.FragmentCharacterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterFragment : Fragment(R.layout.fragment_character) {

    private val binding by viewBinding<FragmentCharacterBinding>()
    private val viewModel by viewModels<CharacterViewModel>()
    private val adapter = InventoryAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.inventory.adapter = adapter
        binding.inventory.setHasFixedSize(true)

        viewModel.inventory.observe(viewLifecycleOwner) {
            println(it)
            adapter.submitList(it)
        }
    }
}