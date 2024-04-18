package com.freecast.thatmovieapp.categories


import android.content.Context
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.freecast.thatmovieapp.R
import com.freecast.thatmovieapp.core.ui.BaseFragment
import com.freecast.thatmovieapp.domain.model.Genre
import com.freecast.thatmovieapp.movies.OnRefreshMoviesListener
import com.freecast.thatmovieapp.util.Constants

class GenresFragment : BaseFragment<GenresViewModel>(R.layout.fragment_genres,GenresViewModel::class.java), View.OnClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: GenresAdapter
    var onRefreshMoviesListener: OnRefreshMoviesListener? = null

    override fun onInitViews() {
        super.onInitViews()
        recyclerView = findViewByID(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    override fun onInitObservers() {
        super.onInitObservers()
        viewModel.fetchGenres().observe(viewLifecycleOwner) {
            initGenres(it)
        }
    }

    private fun initGenres(genres: List<Genre>) {
        adapter = GenresAdapter(genres, this)
        recyclerView.adapter = adapter
    }

    override fun onClick(v: View) {
        val position = v.tag as Int
        if (adapter.selectedPosition == position) {
            adapter.selectedPosition = -1
            notifyLoadMoviesByGenre(null)
        } else {
            adapter.selectedPosition = position
            notifyLoadMoviesByGenre(adapter.getGenreByPosition(position))
        }
        adapter.notifyDataSetChanged()
    }

    private fun notifyLoadMoviesByGenre(genre: Genre?) {
        if (genre == null) {
            onRefreshMoviesListener?.onLoadMovies(getString(R.string.main_popular_movies), Constants.MoviesEndPoint.POPULAR)
        } else {
            onRefreshMoviesListener?.onLoadMoviesByGenreId(genre.name,  genre.id)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(onRefreshMoviesListener: OnRefreshMoviesListener? = null) = GenresFragment().apply {
            this.onRefreshMoviesListener = onRefreshMoviesListener
        }
    }
}