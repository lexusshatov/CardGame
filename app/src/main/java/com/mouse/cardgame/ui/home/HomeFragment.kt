package com.mouse.cardgame.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mouse.cardgame.R
import com.mouse.cardgame.core.usecase.GetLevelProgressUseCase
import com.mouse.cardgame.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding<FragmentHomeBinding>()
    private val viewModel by viewModels<HomeViewModel>()

    @Inject
    lateinit var getLevelProgressUseCase: GetLevelProgressUseCase

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.user.observe(viewLifecycleOwner) { user ->
            binding.username.text = user.name
            binding.lvl.text = user.lvl.toString()
            lifecycleScope.launch(Dispatchers.IO) {
                val progress = getLevelProgressUseCase.run(
                    GetLevelProgressUseCase.Params(user.lvl, user.experience))
                withContext(Dispatchers.Main) { binding.lvlProgress.progress = progress }
            }
        }

        binding.lvlUp.setOnClickListener {
            viewModel.addExperience(10L)
        }
    }
}