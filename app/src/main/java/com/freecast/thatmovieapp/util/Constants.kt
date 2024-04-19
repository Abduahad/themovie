package com.freecast.thatmovieapp.util

object Constants {
    const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500/"
    const val BASE_VIDEO_URL = "https://www.youtube.com/watch?v="

    class MoviesEndPoint {
        companion object {
            const val POPULAR = "movie/popular"
            const val UPCOMING = "movie/upcoming"
            const val TOP_RATED = "movie/top_rated"
            const val NOW_PLAYING = "movie/now_playing"
        }
    }
}