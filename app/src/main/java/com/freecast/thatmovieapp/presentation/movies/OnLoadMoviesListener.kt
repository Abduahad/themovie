package com.freecast.thatmovieapp.presentation.movies

interface OnLoadMoviesListener {
    fun onLoadMoviesByGenre(title: String, genreId: Int)

    fun onLoadMoviesByEndPoint(title: String, endPoint: String)

    fun onLoadDefaultMovies()
}