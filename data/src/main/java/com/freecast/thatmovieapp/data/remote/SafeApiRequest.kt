package com.freecast.thatmovieapp.data.remote

import android.content.Context
import com.freecast.thatmovieapp.data.remote.exceptions.BaseException
import com.freecast.thatmovieapp.data.remote.exceptions.NetworkException
import com.freecast.thatmovieapp.domain.model.Resource

import retrofit2.Response
import java.io.IOException
import java.lang.Exception
import java.net.ConnectException

//ToDo: Refactor this class to add more exception handling
abstract class SafeApiRequest(protected val context: Context) {

    suspend fun <T : Any, R : Any> sendRequest(
        call: suspend () -> Response<T>,
        mapper: (T) -> R
    ): Resource<R> {
        return try {
            if (!NetworkUtils.isNetworkAvailable(context)) {
                return Resource.Error(NetworkException(null))
            }
            val response = call.invoke()
            val mappedResult = mapper(response.body()!!)
            Resource.Success(mappedResult)
        } catch (e: BaseException) {
            Resource.Error(e)
        } catch (e: ConnectException) {
            Resource.Error(NetworkException(null))
        } catch (e: InterruptedException) {
            Resource.Error(NetworkException(null))
        } catch (e: IOException) {
            Resource.Error(BaseException(0, e.message))
        } catch (e: Exception) {
            Resource.Error(BaseException(0, e.message))
        }
    }
}