package com.freecast.thatmovieapp.core.util

import android.content.Context
import android.widget.Toast
import com.freecast.thatmovieapp.core.R
import com.freecast.thatmovieapp.data.remote.exceptions.NetworkException
import com.freecast.thatmovieapp.data.remote.exceptions.ServerException
//ToDo: Refactor this class to use the new exceptions. This is not good practice
class ErrorHandler(private val context: Context) {

    fun handleError(exception: Any) {
        val message = when (exception) {
            is NetworkException -> context.getString(R.string.error_network)
            is ServerException -> context.getString(R.string.error_server)
            else -> context.getString(R.string.error_unknown)
        }

        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}