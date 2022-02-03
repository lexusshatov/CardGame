package com.mauz.narutogame.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mauz.narutogame.R
import com.mauz.narutogame.core.usecase.GetLevelProgressUseCase
import com.mauz.narutogame.databinding.FragmentHomeBinding
import com.mauz.narutogame.util.MarginDecorator
import com.mauz.narutogame.util.dp
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding<FragmentHomeBinding>()
    private val viewModel by viewModels<HomeViewModel>()

    @Inject
    lateinit var getLevelProgressUseCase: GetLevelProgressUseCase

    private val adapter = ButtonsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.user.observe(viewLifecycleOwner) { user ->
            binding.username.text = user.name
            binding.lvl.text = user.lvl.toString()
            getLevelProgressUseCase(
                params = GetLevelProgressUseCase.Params(user.lvl, user.experience),
                scope = lifecycleScope
            ) { binding.lvlProgress.progress = it }
        }

        binding.lvlUp.setOnClickListener {
            viewModel.addExperience(10L)
        }

        with(binding.buttons) {
            layoutManager = GridLayoutManager(requireContext(), 2)
            addItemDecoration(MarginDecorator(horizontal = 10.dp))
            adapter = this@HomeFragment.adapter
        }

        adapter.submitList(listOf(
            getString(R.string.bag),
            getString(R.string.ninja_village),
            getString(R.string.journey),
            getString(R.string.tailed_beast),
            getString(R.string.chat)
        ))
    }
}