package com.freecast.thatmovieapp.domain.core

abstract class BaseUseCase<T, Params> {
    abstract suspend fun execute(params: Params): T
}