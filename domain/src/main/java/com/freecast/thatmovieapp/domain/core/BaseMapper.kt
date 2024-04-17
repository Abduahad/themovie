package com.freecast.thatmovieapp.domain.core

abstract class BaseMapper<From, To> {

    abstract fun map(from: From): To

    fun map(fromList: List<From>): List<To> {
        return fromList.map { map(it) }
    }
}