package com.freecast.thatmovieapp.core

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import com.freecast.thatmovieapp.R
import com.freecast.thatmovieapp.core.ui.BaseFragment
import com.freecast.thatmovieapp.presentation.detail.DetailMovieFragment
import com.freecast.thatmovieapp.domain.model.MovieEntity
import com.freecast.thatmovieapp.presentation.movies.MoviesViewModel

abstract class BaseMoviesFragment<VM : MoviesViewModel>(@LayoutRes layoutResID: Int, mVModelClass: Class<VM>) : BaseFragment<VM>(layoutResID, mVModelClass), View.OnClickListener {
    protected val ENDPOINT = "ENDPOINT"
    protected val TITLE = "TITLE"
    protected val MOVIE_ID = "MovieID"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            viewModel.movieId = it.getInt(MOVIE_ID)
        }
        arguments?.getString(ENDPOINT)?.let {
            viewModel.endPoint = it
        }
        arguments?.getString(TITLE)?.let {
            viewModel.title = it
        }
    }

    override fun onInitObservers() {
        super.onInitObservers()
        fetchMovies()
    }

    protected fun fetchMovies() {
        viewModel.fetchMovies().observe(this) {
            initMovies(it)
        }
    }

    protected fun fetchMoviesByGenreId(genreId: Int) {
        viewModel.fetchMoviesByGenreId(genreId).observe(this) {
            initMovies(it)
        }
    }

    override fun onClick(v: View?) {
        v?.let {
            transaction(
                R.id.fragment_container,
                DetailMovieFragment.newInstance(it.tag as Int),
                true,
                isReplace = false
            )
        }
    }

    abstract fun initMovies(movies: List<MovieEntity>)
}