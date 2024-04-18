package com.freecast.thatmovieapp.movies

interface OnRefreshMoviesListener {
    fun onLoadMovies(title: String, endpoint:String)
    fun onLoadMoviesByGenreId(title: String, genreId:Int)
}