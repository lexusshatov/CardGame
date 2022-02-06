package com.mauz.narutogame.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.mauz.narutogame.R
import com.mauz.narutogame.core.RegisterState
import com.mauz.narutogame.core.repository.ResourceProvider
import com.mauz.narutogame.core.repository.UserRepository
import com.mauz.narutogame.util.toSingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider,
    private val userRepository: UserRepository,
) : ViewModel() {

    private val _register = MutableLiveData<NavDirections>()
    val register: LiveData<NavDirections> = _register.toSingleEvent()

    fun register(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val direction = when (userRepository.register(name)) {
                RegisterState.NameAlreadyExist -> RegisterFragmentDirections.actionRegisterFragmentToInfoDialog(
                    info = resourceProvider.getResources().getString(R.string.name_already_used))
                RegisterState.Success -> RegisterFragmentDirections.actionRegisterFragmentToMainActivity()
            }
            _register.postValue(direction)
        }
    }
}
