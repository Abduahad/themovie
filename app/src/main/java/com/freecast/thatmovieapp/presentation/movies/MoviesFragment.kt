package com.freecast.thatmovieapp.presentation.movies


import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.freecast.thatmovieapp.R
import com.freecast.thatmovieapp.domain.model.Movie
import com.freecast.thatmovieapp.core.BaseMoviesFragment

class MoviesFragment : BaseMoviesFragment<MoviesViewModel>(R.layout.fragment_movies, MoviesViewModel::class.java), OnRefreshMoviesListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var textView: TextView
    private val adapter: MoviesAdapter = MoviesAdapter(this)
    override fun onInitViews() {
        super.onInitViews()
        recyclerView = findViewByID(R.id.recyclerView)
        progressBar = findViewByID(R.id.progressBar)
        textView = findViewByID(R.id.textViewTitle)
        textView.text = viewModel.title
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter
    }

    override fun isLoading(isLoading: Boolean) {
        if (isLoading) {
            progressBar.visibility = View.VISIBLE
            recyclerView.visibility = View.INVISIBLE
        } else {
            progressBar.visibility = View.INVISIBLE
            recyclerView.visibility = View.VISIBLE
        }
    }

    override fun initMovies(movies: List<Movie>) {
        adapter.setMovies(movies)
    }

    companion object {
        @JvmStatic
        fun newInstance(endPoint: String, title: String) =
            MoviesFragment().apply {
                arguments = Bundle().apply {
                    putString(ENDPOINT, endPoint)
                    putString(TITLE, title)
                }
            }

        fun newInstance(movieId: Int, title: String) =
            MoviesFragment().apply {
                arguments = Bundle().apply {
                    putInt(MOVIE_ID, movieId)
                    putString(TITLE, title)
                }
            }
    }

    override fun onLoadMovies(title: String, endpoint: String) {
        textView.text = title
        viewModel.endPoint = endpoint
        fetchMovies()
    }

    override fun onLoadMoviesByGenreId(title: String, genreId: Int) {
        textView.text = title
        fetchMoviesByGenreId(genreId)
    }

}