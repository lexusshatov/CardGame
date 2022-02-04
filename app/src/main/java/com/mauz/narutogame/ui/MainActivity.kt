package com.mauz.narutogame.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mauz.narutogame.R
import com.mauz.narutogame.databinding.ActivityMainBinding
import com.mauz.narutogame.util.hideSystemUI
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding by viewBinding<ActivityMainBinding>()
    private val navController: NavController
        get() = (supportFragmentManager.findFragmentById(binding.fragmentContainer.id) as NavHostFragment).navController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.bottomNav.setupWithNavController(navController)
    }

    override fun onResume() {
        super.onResume()
        window.hideSystemUI()
    }
}