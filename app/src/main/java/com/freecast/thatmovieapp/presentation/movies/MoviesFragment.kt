package com.freecast.thatmovieapp.presentation.movies


import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.freecast.thatmovieapp.R
import com.freecast.thatmovieapp.core.ui.BaseFragment
import com.freecast.thatmovieapp.presentation.detail.DetailMovieFragment

class MoviesFragment : BaseFragment<MoviesViewModel>(R.layout.fragment_movies, MoviesViewModel::class.java), OnRefreshMoviesListener, View.OnClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var textView: TextView
    private val adapter: MoviesAdapter = MoviesAdapter(this)

    override fun onInitViews() {
        super.onInitViews()
        recyclerView = findViewByID(R.id.recyclerView)
        progressBar = findViewByID(R.id.progressBar)
        textView = findViewByID(R.id.textViewTitle)
        arguments?.getString(ENDPOINT)?.let {
            textView.text = it
        }
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter
    }

    override fun onInitObservers() {
        super.onInitObservers()
        var title = getString(R.string.main_popular_movies)
        arguments?.let { bundle ->
            bundle.getString(TITLE)?.let {
                title = it
            }
            bundle.getString(ENDPOINT)?.let {
                onLoadMovies(title, it)
            }
            if (bundle.containsKey(GENRE_ID)) {
                onLoadMoviesByGenreId(title, bundle.getInt(GENRE_ID))
            }
        }
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

    override fun onLoadMovies(title: String, endpoint: String) {
        viewModel.fetchMovies(endpoint).observe(this) {
            adapter.setMovies(it)
        }
        textView.text = title
    }

    override fun onLoadMoviesByGenreId(title: String, genreId: Int) {
        viewModel.fetchMoviesByGenreId(genreId).observe(this) {
            adapter.setMovies(it)
        }
        textView.text = title
    }

    override fun onClick(p0: View?) {
        p0?.let {
            transaction(
                R.id.fragment_container,
                DetailMovieFragment.newInstance(it.tag as Int),
                true,
                isReplace = false
            )
        }
    }

    companion object {
        private const val ENDPOINT = "ENDPOINT"
        private const val TITLE = "TITLE"
        private const val GENRE_ID = "GENRE_ID"

        @JvmStatic
        fun newInstance(endPoint: String, title: String) =
            MoviesFragment().apply {
                arguments = Bundle().apply {
                    putString(ENDPOINT, endPoint)
                    putString(TITLE, title)
                }
            }

        @JvmStatic
        fun newInstance(genreId: Int, title: String) =
            MoviesFragment().apply {
                arguments = Bundle().apply {
                    putInt(GENRE_ID, genreId)
                    putString(TITLE, title)
                }
            }
    }

}