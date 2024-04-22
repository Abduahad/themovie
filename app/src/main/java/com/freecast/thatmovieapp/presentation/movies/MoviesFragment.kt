package com.freecast.thatmovieapp.presentation.movies


import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.freecast.thatmovieapp.R
import com.freecast.thatmovieapp.core.ui.BaseFragmentWithoutVM
import com.freecast.thatmovieapp.core.util.ErrorHandler
import com.freecast.thatmovieapp.presentation.detail.DetailMovieFragment
import com.freecast.thatmovieapp.util.Constants

class MoviesFragment : BaseFragmentWithoutVM(R.layout.fragment_movies), OnLoadMoviesListener, View.OnClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var textView: TextView
    private lateinit var viewModel: MoviesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var isTv = false
        arguments?.let {
            if (it.containsKey(IS_TV)) isTv = it.getBoolean(IS_TV)
        }
        viewModel = ViewModelProvider(this, MoviesVMFactory(isTv)).get(MoviesViewModel::class.java)
    }

    private val adapter: MoviesAdapter = MoviesAdapter(this)

    override fun onInitViews() {
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
        viewModel.errorHandler.observe(viewLifecycleOwner) {
            ErrorHandler(requireContext()).handleError(it)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            isLoading(it)
        }
        var title: String = ""
        arguments?.let { bundle ->
            bundle.getString(TITLE)?.let {
                title = it
            }
            bundle.getString(ENDPOINT)?.let {
                onLoadMoviesByEndPoint(title, it)
            }

            if (bundle.containsKey(GENRE_ID)) {
                onLoadMoviesByGenre(title, bundle.getInt(GENRE_ID))
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

    override fun onLoadMoviesByGenre(title: String, genreId: Int) {
        viewModel.fetchMoviesByGenreId(genreId).observe(this) {
            adapter.setMovies(it)
        }
        textView.text = title
    }

    override fun onLoadMoviesByEndPoint(title: String, endPoint: String) {
        viewModel.fetchMovies(endPoint).observe(this) {
            adapter.setMovies(it)
        }
        textView.text = title
    }

    override fun onLoadDefaultMovies() {
        if (viewModel.getIsTv()) {
            onLoadMoviesByEndPoint(getString(R.string.main_popular_tv), Constants.MoviesEndPoint.POPULAR)
        } else {
            onLoadMoviesByEndPoint(getString(R.string.main_popular_movies), Constants.MoviesEndPoint.POPULAR)
        }
    }

    override fun onClick(p0: View?) {
        p0?.let {
            transaction(
                R.id.fragment_container,
                DetailMovieFragment.newInstance(it.tag as Int,viewModel.isTv),
                true,
                isReplace = false
            )
        }
    }

    companion object {
        private const val ENDPOINT = "ENDPOINT"
        private const val TITLE = "TITLE"
        private const val GENRE_ID = "GENRE_ID"
        private const val IS_TV = "IS_TV"

        @JvmStatic
        fun newInstance(endPoint: String, title: String, isTv: Boolean = false) =
            MoviesFragment().apply {
                arguments = Bundle().apply {
                    putString(ENDPOINT, endPoint)
                    putString(TITLE, title)
                    putBoolean(IS_TV, isTv)
                }
            }

        @JvmStatic
        fun newInstance(genreId: Int, title: String, isTv: Boolean = false) =
            MoviesFragment().apply {
                arguments = Bundle().apply {
                    putInt(GENRE_ID, genreId)
                    putString(TITLE, title)
                    putBoolean(IS_TV, isTv)
                }
            }
    }

}