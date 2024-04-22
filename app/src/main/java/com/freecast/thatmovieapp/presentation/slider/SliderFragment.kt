package com.freecast.thatmovieapp.presentation.slider

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ProgressBar
import androidx.viewpager2.widget.ViewPager2
import com.freecast.thatmovieapp.R
import com.freecast.thatmovieapp.domain.model.MovieEntity
import com.freecast.thatmovieapp.core.BaseMoviesFragment
import com.freecast.thatmovieapp.util.Constants

class SliderFragment : BaseMoviesFragment<SliderViewModel>(R.layout.fragment_movies_pager, SliderViewModel::class.java) {
    private lateinit var viewPager2: ViewPager2
    private lateinit var progressBar: ProgressBar
    private val sliderHandler = Handler(Looper.getMainLooper())

    override fun onInitViews() {
        super.onInitViews()
        progressBar = findViewByID(R.id.progressBar)
        viewPager2 = findViewByID(R.id.viewPager)
        viewPager2.offscreenPageLimit = 3
        viewPager2.clipChildren = false
        viewPager2.clipToPadding = false
        viewPager2.setPageTransformer(viewModel.getPageTransformer())
    }

    override fun initMovies(movies: List<MovieEntity>) {
        viewPager2.adapter = SliderAdapter(movies,this)
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

    private val sliderRunnable = Runnable {
        viewPager2.currentItem += 1
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            SliderFragment().apply {
                arguments = Bundle().apply {
                    putString(ENDPOINT, Constants.MoviesEndPoint.NOW_PLAYING)
                }
            }
    }
}