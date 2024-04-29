package com.freecast.thatmovieapp.presentation.slider

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ProgressBar
import androidx.viewpager2.widget.ViewPager2
import com.freecast.thatmovieapp.R
import com.freecast.thatmovieapp.core.ui.BaseFragment
import com.freecast.thatmovieapp.domain.model.MovieEntity
import com.freecast.thatmovieapp.presentation.detail.DetailMovieFragment
import com.freecast.thatmovieapp.util.Constants

class SliderFragment : BaseFragment<SliderViewModel>(R.layout.fragment_movies_pager, SliderViewModel::class.java), View.OnClickListener {
    private lateinit var viewPager2: ViewPager2
    private lateinit var progressBar: ProgressBar
    private val sliderRunnable = Runnable { viewPager2.currentItem += 1 }
    private val sliderHandler = Handler(Looper.getMainLooper())

    override fun onInitViews() {
        progressBar = findViewByID(R.id.progressBar)
        viewPager2 = findViewByID(R.id.viewPager)
        viewPager2.offscreenPageLimit = 3
        viewPager2.clipChildren = false
        viewPager2.clipToPadding = false
        viewPager2.setPageTransformer(viewModel.getPageTransformer())
    }

    override fun onInitObservers() {
        arguments?.getString(ENDPOINT)?.let {
            viewModel.fetchMovies(it).observe(this) {
                initMovies(it)
            }
        }
    }

    override fun isLoading(isLoading: Boolean) {
        if (isLoading) {
            progressBar.visibility = View.VISIBLE
            viewPager2.visibility = View.INVISIBLE
        } else {
            progressBar.visibility = View.INVISIBLE
            viewPager2.visibility = View.VISIBLE
        }
    }

    override fun onResume() {
        super.onResume()
        sliderHandler.postDelayed(sliderRunnable, 2000)
    }

    private fun initMovies(movies: List<MovieEntity>) {
        viewPager2.adapter = SliderAdapter(movies, this)
        viewPager2.getChildAt(0).overScrollMode = ViewPager2.OVER_SCROLL_NEVER
        viewPager2.currentItem = 1
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == movies.size - 1) {
                    sliderHandler.removeCallbacks(sliderRunnable)
                }
            }
        })
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

    companion object {
        private const val ENDPOINT = "ENDPOINT"
        private const val TITLE = "TITLE"

        @JvmStatic
        fun newInstance() =
            SliderFragment().apply {
                arguments = Bundle().apply {
                    putString(ENDPOINT, Constants.MoviesEndPoint.NOW_PLAYING)
                }
            }
    }
}