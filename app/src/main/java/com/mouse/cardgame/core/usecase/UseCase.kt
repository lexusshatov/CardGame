package com.mouse.cardgame.core.usecase

import kotlinx.coroutines.*

abstract class UseCase<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Type

    operator fun invoke(
        params: Params,
        scope: CoroutineScope,
        onResult: (Type) -> Unit = {}
    ) {
        scope.launch(Dispatchers.IO) {
            val result = run(params)
            withContext(Dispatchers.Main) {
                onResult(result)
            }
        }
    }
}