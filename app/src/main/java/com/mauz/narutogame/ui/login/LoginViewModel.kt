package com.mauz.narutogame.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.mauz.narutogame.core.LoginResult
import com.mauz.narutogame.core.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {

    private val _login = MutableLiveData<NavDirections>()
    val login: LiveData<NavDirections> = _login

    fun login() {
        viewModelScope.launch(Dispatchers.IO) {
            val direction = when (userRepository.login()) {
                LoginResult.Success -> LoginFragmentDirections.actionLoginFragmentToMainActivity()
                LoginResult.UserNotRegistered -> LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            }
            _login.postValue(direction)
        }
    }
}
