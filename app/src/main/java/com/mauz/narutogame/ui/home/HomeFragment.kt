package com.mauz.narutogame.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mauz.narutogame.R
import com.mauz.narutogame.databinding.FragmentHomeBinding
import com.mauz.narutogame.ui.base.MarginDecorator
import com.mauz.narutogame.util.dp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding<FragmentHomeBinding>()
    private val viewModel by viewModels<HomeViewModel>()
    private val adapter = ButtonsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.user.observe(viewLifecycleOwner) { user ->
            binding.username.text = user.name
            binding.lvl.text = user.lvl.toString()
            Glide.with(binding.icon)
                .load(user.photo)
                .error(R.drawable.ic_user)
                .placeholder(R.drawable.ic_user)
                .into(binding.icon)
        }

        with(binding.buttons) {
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

        // FIXME: 05.02.2022 delete only for test
        binding.testlvlup.setOnClickListener {
            Firebase.firestore.collection("users")
                .document(Firebase.auth.currentUser!!.uid)
                .update(
                    mapOf(
                        "lvl" to binding.lvl.text.toString().toInt() + 1
                    )
                )
        }
    }
}