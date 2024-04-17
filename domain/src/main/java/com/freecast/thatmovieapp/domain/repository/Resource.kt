package com.freecast.thatmovieapp.domain.repository

import java.io.IOException

sealed class Resource<T>(
    val data: T? = null,
    val error: IOException? = null
) {
    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(error: IOException, data: T? = null) : Resource<T>(data, error)
    class Loading<T>(val isLoading: Boolean = true) : Resource<T>(null)
}