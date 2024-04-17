package com.freecast.thatmovieapp.data.remote

import com.freecast.thatmovieapp.data.remote.exceptions.BaseException
import com.freecast.thatmovieapp.domain.repository.Resource
import retrofit2.Response

abstract class SafeApiRequest {

    suspend fun <T : Any, R : Any> sendRequest(
        call: suspend () -> Response<T>,
        mapper: (T) -> R
    ): Resource<R> {
        return try {
            val response = call.invoke()
            val mappedResult = mapper(response.body()!!)
            Resource.Success(mappedResult)
        } catch (e: BaseException) {
            Resource.Error(e)
        }
    }
}


