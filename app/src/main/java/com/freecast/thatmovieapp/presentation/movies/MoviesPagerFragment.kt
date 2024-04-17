package com.freecast.thatmovieapp.presentation.movies

import android.os.Bundle
import com.freecast.thatmovieapp.R
import com.freecast.thatmovieapp.domain.model.Movie


class MoviesPagerFragment : BaseMoviesFragment(R.layout.fragment_movies_pager) {

    companion object {
        private const val MOVIES = "MOVIES"
        @JvmStatic
        fun newInstance(movies:ArrayList<Movie>) =
            MoviesPagerFragment().apply {

            }
    }
}