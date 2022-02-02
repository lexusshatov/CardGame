package com.mouse.cardgame.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mouse.cardgame.R
import com.mouse.cardgame.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private val binding by viewBinding<FragmentLoginBinding>()
    private val viewModel by viewModels<LoginViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.user.observe(viewLifecycleOwner) {
            findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToNavGraph())
        }

        binding.login.setOnClickListener {
            with(binding.appCompatEditText.text.toString()) {
                if (isNotEmpty()) viewModel.login(this)
            }
        }
    }
}