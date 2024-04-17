package com.freecast.thatmovieapp.data.remote.interceptors

import com.freecast.thatmovieapp.data.remote.exceptions.BadException
import com.freecast.thatmovieapp.data.remote.exceptions.BaseException
import com.freecast.thatmovieapp.data.remote.exceptions.ServerException
import okhttp3.Interceptor
import okhttp3.Response
import okio.IOException

class ErrorInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        if (!response.isSuccessful) {
            when (response.code) {
                400 -> throw BadException(400,response.message)
                401 -> throw BadException(401,response.message)
                403 -> throw BadException(403,response.message)
                404 -> throw BadException(404,response.message)
                500 -> throw ServerException(500,response.message)
                else -> throw BaseException(response.code,response.message)
            }
        }

        return response
    }
}