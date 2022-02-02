package com.mauz.narutogame.core.usecase

import com.mauz.narutogame.core.db.NarutoDao
import javax.inject.Inject
import kotlin.math.roundToInt

class GetLevelProgressUseCase @Inject constructor(
    private val narutoDao: NarutoDao
) : UseCase<Int, GetLevelProgressUseCase.Params>() {

    data class Params(
        val lvl: Int,
        val experience: Long,
    )

    override suspend fun run(params: Params): Int {
        val expectedExperience = narutoDao.getExpectedExperience(params.lvl)
        return (params.experience.toDouble() / expectedExperience * 100).roundToInt()
    }
}