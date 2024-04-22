package com.freecast.thatmovieapp.presentation.home


import android.os.Bundle
import android.view.View
import com.freecast.thatmovieapp.R
import com.freecast.thatmovieapp.presentation.genres.GenresFragment
import com.freecast.thatmovieapp.core.ui.BaseFragmentWithoutVM
import com.freecast.thatmovieapp.presentation.movies.MoviesFragment
import com.freecast.thatmovieapp.presentation.slider.SliderFragment
import com.freecast.thatmovieapp.util.Constants

//ToDo:Refactor this fragment to show dynamically the movies and tv shows
class HomeFragment : BaseFragmentWithoutVM(R.layout.fragment_home) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeMovies()
        initializeTVShows()
    }

    private fun initializeMovies() {
        transaction(R.id.fragmentContainerNowPlayingMovies, SliderFragment.newInstance())
        transaction(R.id.fragmentContainerTopRatedMovies, MoviesFragment.newInstance(Constants.MoviesEndPoint.TOP_RATED, getString(R.string.main_upcoming_top_rated)))

        val moviesFragmentPopular = MoviesFragment.newInstance(Constants.MoviesEndPoint.POPULAR, getString(R.string.main_popular_movies))
        transaction(R.id.fragmentContainerGenres, GenresFragment.newInstance(false).apply { setOnRefreshListener(moviesFragmentPopular) })
        transaction(R.id.fragmentContainerTrendingMovies, moviesFragmentPopular)
        transaction(R.id.fragmentContainerUpcomingMovies, MoviesFragment.newInstance(Constants.MoviesEndPoint.UPCOMING, getString(R.string.main_upcoming_movies)))
    }

    private fun initializeTVShows() {
        transaction(R.id.fragmentContainerTopRatedTv, MoviesFragment.newInstance(Constants.MoviesEndPoint.TOP_RATED, getString(R.string.main_upcoming_top_rated_tv), true))
        val tvShowsFragmentPopular = MoviesFragment.newInstance(Constants.MoviesEndPoint.POPULAR, getString(R.string.main_popular_tv), true)
        transaction(R.id.fragmentContainerGenresTv, GenresFragment.newInstance(true).apply { setOnRefreshListener(tvShowsFragmentPopular) })
        transaction(R.id.fragmentContainerTrendingTv, tvShowsFragmentPopular)
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }

}