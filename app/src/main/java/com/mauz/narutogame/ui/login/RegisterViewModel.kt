package com.mauz.narutogame.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.mauz.narutogame.core.RegisterResult
import com.mauz.narutogame.core.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {

    private val _register = MutableLiveData<NavDirections>()
    val register: LiveData<NavDirections> = _register

    fun register(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val direction = when (userRepository.register(name)) {
                RegisterResult.NameAlreadyExist -> RegisterFragmentDirections.actionRegisterFragmentToInfoDialog(
                    info = "Данное имя уже используется")
                RegisterResult.Success -> RegisterFragmentDirections.actionRegisterFragmentToMainActivity()
            }
            _register.postValue(direction)
        }
    }
}
