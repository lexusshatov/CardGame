package com.mauz.narutogame.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mauz.narutogame.core.db.NarutoDao
import com.mauz.narutogame.core.repository.UserRepository
import com.mauz.narutogame.data.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val narutoDao: NarutoDao,
    private val userRepository: UserRepository,
) : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    init {
        login(userRepository.getUsername())
    }

    fun login(username: String) {
        if (!validateUsername(username)) return

        viewModelScope.launch(Dispatchers.IO) {
            if (!narutoDao.isExist(username)) {
                val user = User(username)
                narutoDao.addUser(user)
            }
            userRepository.setUsername(username)
            _user.postValue(narutoDao.getUser(username))
        }
    }

    private fun validateUsername(username: String): Boolean {
        return username.isNotEmpty()
    }
}