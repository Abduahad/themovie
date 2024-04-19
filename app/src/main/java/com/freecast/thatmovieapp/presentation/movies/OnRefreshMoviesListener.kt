package com.freecast.thatmovieapp.presentation.movies

interface OnRefreshMoviesListener {
    fun onLoadMovies(title: String, endpoint:String)
    fun onLoadMoviesByGenreId(title: String, genreId:Int)
}