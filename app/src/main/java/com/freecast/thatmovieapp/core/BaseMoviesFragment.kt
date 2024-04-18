package com.freecast.thatmovieapp.core

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.freecast.thatmovieapp.core.ui.BaseFragment
import com.freecast.thatmovieapp.domain.model.Movie
import com.freecast.thatmovieapp.movies.MoviesViewModel
import com.freecast.thatmovieapp.movies.MoviesViewModelFactory

abstract class BaseMoviesFragment<VM : MoviesViewModel>(@LayoutRes layoutResID: Int, private val mVModelClass: Class<VM>) : BaseFragment(layoutResID), View.OnClickListener {
    protected val ENDPOINT = "ENDPOINT"
    protected val TITLE = "TITLE"
    protected lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var endPoint: String = "popular"
        var title: String = ""

        arguments?.getString(ENDPOINT)?.let {
            endPoint = it
        }
        arguments?.getString(TITLE)?.let {
            title = it
        }
        viewModel = ViewModelProvider(this, MoviesViewModelFactory(endPoint, title))[mVModelClass]
    }

    override fun onInitObservers() {
        super.onInitObservers()
        viewModel.errorHandler.observe(viewLifecycleOwner, {
            //showToast(it)
        })

        viewModel.isLoading.observe(this, Observer {
            isLoading(it)
        })

        fetchMovies()
    }

    protected fun fetchMovies() {
        viewModel.fetchMovies().observe(this, Observer {
            initMovies(it)
        })
    }

    protected fun fetchMoviesByGenreId(genreId: Int) {
        viewModel.fetchMoviesByGenreId(genreId).observe(this, Observer {
            initMovies(it)
        })
    }

    override fun onClick(v: View?) {

    }

    abstract fun isLoading(isLoading: Boolean)
    abstract fun initMovies(movies: List<Movie>)
}