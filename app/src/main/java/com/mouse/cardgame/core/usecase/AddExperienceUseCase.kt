package com.mouse.cardgame.core.usecase

import com.mouse.cardgame.core.db.NarutoDao
import com.mouse.cardgame.core.repository.UserRepository
import javax.inject.Inject

class AddExperienceUseCase @Inject constructor(
    private val narutoDao: NarutoDao,
    private val userRepository: UserRepository,
) : UseCase<Unit, Long>() {

    override suspend fun run(params: Long) {
        val user = narutoDao.getUser(userRepository.getUsername())
        val actualExperience = user.experience + params
        val expectedExperience = narutoDao.getExpectedExperience(user.lvl)
        val updatedUser = if (actualExperience >= expectedExperience) {
            user.copy(lvl = user.lvl + 1, experience = actualExperience - expectedExperience)
        } else {
            user.copy(experience = actualExperience)
        }
        narutoDao.updateUser(updatedUser)
    }
}

