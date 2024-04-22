package com.freecast.thatmovieapp.util

object Constants {
    const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500/"
    const val BASE_VIDEO_URL = "https://www.youtube.com/watch?v="

    class MoviesEndPoint {
        companion object {
            const val POPULAR= "popular"
            const val UPCOMING = "upcoming"
            const val TOP_RATED = "top_rated"
            const val NOW_PLAYING = "now_playing"
        }
    }
}