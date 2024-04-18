package com.freecast.thatmovieapp


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.freecast.thatmovieapp.categories.GenresFragment
import com.freecast.thatmovieapp.core.ui.BaseFragmentWithoutVM
import com.freecast.thatmovieapp.movies.MoviesFragment
import com.freecast.thatmovieapp.slider.SliderFragment
import com.freecast.thatmovieapp.util.Constants

class HomeFragment : BaseFragmentWithoutVM(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        transaction(R.id.fragmentContainerNowPlayingMovies, SliderFragment.newInstance())
        transaction(R.id.fragmentContainerTopRatedMovies, MoviesFragment.newInstance(Constants.MoviesEndPoint.TOP_RATED, getString(R.string.main_upcoming_top_rated)))

        val moviesFragmentPopular = MoviesFragment.newInstance(Constants.MoviesEndPoint.POPULAR, getString(R.string.main_popular_movies))
        transaction(R.id.fragmentContainerGenres, GenresFragment.newInstance(moviesFragmentPopular))
        transaction(R.id.fragmentContainerTrendingMovies, moviesFragmentPopular)
        transaction(R.id.fragmentContainerUpcomingMovies, MoviesFragment.newInstance(Constants.MoviesEndPoint.UPCOMING, getString(R.string.main_upcoming_movies)))
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}