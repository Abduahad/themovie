package com.freecast.thatmovieapp.core.utils

import android.content.Context
import android.widget.Toast
import com.freecast.thatmovieapp.core.R
import com.freecast.thatmovieapp.data.remote.exceptions.NetworkException
import com.freecast.thatmovieapp.data.remote.exceptions.ServerException
import java.io.IOException

class ErrorHandler(private val context: Context) {

    fun handleError(exception: IOException) {
        val message = when (exception) {
            is NetworkException -> context.getString(R.string.error_network)
            is ServerException -> context.getString(R.string.error_server)
            else -> context.getString(R.string.error_unknown)
        }

        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}