package com.mouse.cardgame.ui.home

import androidx.lifecycle.ViewModel
import com.mouse.cardgame.core.NarutoDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val narutoDao: NarutoDao,
) : ViewModel() {


}