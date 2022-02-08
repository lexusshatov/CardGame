package com.mauz.narutogame.ui.character

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mauz.narutogame.R
import com.mauz.narutogame.core.repository.cloud.InventoryFirebaseRepository
import com.mauz.narutogame.databinding.FragmentCharacterBinding
import com.mauz.narutogame.ui.base.MarginDecorator
import com.mauz.narutogame.util.dp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterFragment : Fragment(R.layout.fragment_character) {

    private val binding by viewBinding<FragmentCharacterBinding>()
    private val viewModel by viewModels<CharacterViewModel>()
    private val adapter = InventoryAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.inventory.adapter = adapter
        binding.inventory.addItemDecoration(MarginDecorator(5.dp))

        viewModel.inventory.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        //FIXME add test
        binding.testaddbutton.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val randomId = (1..11).random().toString()
                InventoryFirebaseRepository().addItem(randomId, 5)
            }
        }
        // FIXME: 08.02.2022 delete test
        binding.testremovebutton.setOnClickListener {
            adapter.focus?.let { item ->
                val direction = CharacterFragmentDirections.actionCharacterToRemoveItemDialog(item)
                findNavController().navigate(direction)
            }
        }
    }
}