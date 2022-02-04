package com.mauz.narutogame.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mauz.narutogame.R
import com.mauz.narutogame.databinding.FragmentHomeBinding
import com.mauz.narutogame.util.MarginDecorator
import com.mauz.narutogame.util.dp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding<FragmentHomeBinding>()
    private val adapter = ButtonsAdapter()
    private val user = Firebase.auth.currentUser!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.username.text = user.displayName
        Glide.with(binding.icon)
            .load(user.photoUrl)
            .error(R.drawable.ic_user)
            .placeholder(R.drawable.ic_user)
            .into(binding.icon)

        binding.lvlUp.setOnClickListener {
            //viewModel.addExperience(10L)
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