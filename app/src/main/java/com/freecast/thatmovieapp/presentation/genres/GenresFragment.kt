package com.freecast.thatmovieapp.presentation.genres


import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.freecast.thatmovieapp.R
import com.freecast.thatmovieapp.core.ui.BaseFragment
import com.freecast.thatmovieapp.domain.model.GenreEntity
import com.freecast.thatmovieapp.presentation.movies.OnLoadMoviesListener

class GenresFragment : BaseFragment<GenresViewModel>(R.layout.fragment_genres, GenresViewModel::class.java) {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: GenresAdapter
    private var onRefreshMoviesListener: OnLoadMoviesListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            viewModel.setTv(it.getBoolean(IS_TV))
        }
    }

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

    private fun initGenres(genres: List<GenreEntity>) {
        adapter = GenresAdapter(genres, viewModel.getTv())
        adapter.setOnRefreshListener(onRefreshMoviesListener)
        recyclerView.adapter = adapter
    }

    fun setOnRefreshListener(onRefreshMoviesListener: OnLoadMoviesListener) {
        this.onRefreshMoviesListener = onRefreshMoviesListener
    }

    companion object {
        private const val IS_TV: String = "IS_TV"

        @JvmStatic
        fun newInstance(isTv: Boolean = false) = GenresFragment().apply {
            arguments = Bundle().apply { putBoolean(IS_TV, isTv) }
        }
    }
}