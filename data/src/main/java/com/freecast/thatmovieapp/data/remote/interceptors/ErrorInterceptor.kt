package com.freecast.thatmovieapp.data.remote.interceptors

import com.freecast.thatmovieapp.data.remote.exceptions.BadException
import com.freecast.thatmovieapp.data.remote.exceptions.BaseException
import com.freecast.thatmovieapp.data.remote.exceptions.ServerException
import okhttp3.Interceptor
import okhttp3.Response
import okio.IOException
import org.json.JSONObject

//ToDo: Refactor this class to use the new exceptions
class ErrorInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        if (!response.isSuccessful) {
            when (response.code) {
                400, 401, 403, 404 -> throw BadException(response.code, getErrorMessage(response))
                500, 502, 503 -> throw ServerException(response.code, null)
                else -> throw BaseException(response.code, response.message)
            }
        }
        return response
    }

    private fun getErrorMessage(response: Response): String? {
        if (response.body != null) {
            val jsonObject: JSONObject = JSONObject(response.body!!.string())
            if (jsonObject.has("status_message")) {
                return jsonObject.getString("status_message")
            }
        }
        return null
    }
}