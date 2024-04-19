package com.freecast.thatmovieapp.data.remote.exceptions

import java.io.IOException

open class BaseException(val code: Int, message: String?): IOException(message) {
}