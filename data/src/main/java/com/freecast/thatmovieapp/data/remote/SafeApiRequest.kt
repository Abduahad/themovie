package com.freecast.thatmovieapp.data.remote

import com.freecast.thatmovieapp.data.remote.exceptions.BaseException
import com.freecast.thatmovieapp.data.remote.exceptions.NetworkException
import com.freecast.thatmovieapp.domain.model.Resource

import retrofit2.Response
import java.io.IOException
import java.net.ConnectException

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
        } catch (e: ConnectException) {
            Resource.Error(NetworkException(0, null))
        } catch (e: InterruptedException) {
            Resource.Error(NetworkException(0, null))
        } catch (e: IOException) {
            Resource.Error(e)
        }
    }
}