package com.mouse.cardgame.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mouse.cardgame.core.db.NarutoDao
import com.mouse.cardgame.core.repository.UserRepository
import com.mouse.cardgame.core.usecase.AddExperienceUseCase
import com.mouse.cardgame.data.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val narutoDao: NarutoDao,
    private val userRepository: UserRepository,
    private val addExperienceUseCase: AddExperienceUseCase,
) : ViewModel() {

    val user: LiveData<User>
        get() = narutoDao.getLiveUser(userRepository.getUsername())

    fun addExperience(experience: Long) {
        addExperienceUseCase(experience, viewModelScope)
    }
}