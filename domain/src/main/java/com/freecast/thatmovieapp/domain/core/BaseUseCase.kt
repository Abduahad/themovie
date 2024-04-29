package com.freecast.thatmovieapp.domain.core

import com.freecast.thatmovieapp.domain.model.Resource
import kotlinx.coroutines.flow.Flow

abstract class BaseUseCase<T, Params>() {
    abstract suspend fun execute(params: Params): Flow<Resource<T>>
}
