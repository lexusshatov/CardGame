package com.mauz.narutogame.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mauz.narutogame.core.db.NarutoDao
import com.mauz.narutogame.core.repository.UserRepository
import com.mauz.narutogame.core.usecase.AddExperienceUseCase
import com.mauz.narutogame.data.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val narutoDao: NarutoDao,
    private val userRepository: UserRepository,
    private val addExperienceUseCase: AddExperienceUseCase,
) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val user = User(name = "username")
            narutoDao.addUser(user)
        }
    }

    val user: LiveData<User>
        get() = narutoDao.getLiveUser(userRepository.getToken())

    fun addExperience(experience: Long) {
        addExperienceUseCase(experience, viewModelScope)
    }
}