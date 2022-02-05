package com.mauz.narutogame.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mauz.narutogame.core.repository.UserRepository
import com.mauz.narutogame.data.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    userRepository: UserRepository,
) : ViewModel() {

    private val _user = userRepository.getUser().asLiveData()
    val user: LiveData<User> = _user
}