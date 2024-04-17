package com.freecast.thatmovieapp.presentation.movies


import com.freecast.thatmovieapp.R
import com.freecast.thatmovieapp.domain.model.Movie


class MoviesFragment : BaseMoviesFragment(R.layout.fragment_movies) {
    companion object {
        private const val MOVIES = "MOVIES"
        @JvmStatic
        fun newInstance(movies:ArrayList<Movie>) =
            MoviesFragment().apply {

            }
    }
}