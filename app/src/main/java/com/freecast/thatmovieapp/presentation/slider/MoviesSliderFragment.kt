package com.freecast.thatmovieapp.presentation.slider

import android.os.Handler
import android.os.Looper
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.freecast.thatmovieapp.R
import com.freecast.thatmovieapp.domain.model.Movie
import com.freecast.thatmovieapp.presentation.movies.BaseMoviesFragment


class MoviesSliderFragment : BaseMoviesFragment(R.layout.fragment_movies_pager) {
     var sliders: ArrayList<Slide> = arrayListOf(
        Slide(1, "https://image.tmdb.org/t/p/w500/xOMo8BRK7PfcJv9JCnx7s5hj0PX.jpg"),
        Slide(2, "https://image.tmdb.org/t/p/w500/xOMo8BRK7PfcJv9JCnx7s5hj0PX.jpg"),
        Slide(3, "https://image.tmdb.org/t/p/w500/xOMo8BRK7PfcJv9JCnx7s5hj0PX.jpg"),
        Slide(4, "https://image.tmdb.org/t/p/w500/xOMo8BRK7PfcJv9JCnx7s5hj0PX.jpg"),
        Slide(5, "https://image.tmdb.org/t/p/w500/xOMo8BRK7PfcJv9JCnx7s5hj0PX.jpg"))
    private lateinit var viewPager2: ViewPager2
    private val sliderHandler = Handler(Looper.getMainLooper())

    override fun onInitViews() {
        super.onInitViews()
        viewPager2 = findViewByID(R.id.viewPager)
        viewPager2.adapter = SliderAdapter(sliders, viewPager2)
        viewPager2.offscreenPageLimit = 3
        viewPager2.getChildAt(0).overScrollMode = ViewPager2.OVER_SCROLL_NEVER
        viewPager2.clipChildren = false
        viewPager2.clipToPadding = false
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer(ViewPager2.PageTransformer() { page, position ->
            val r = 1 - Math.abs(position)
            page.scaleY = 0.85f + r * 0.15f
        })

        viewPager2.setPageTransformer(compositePageTransformer)
        viewPager2.currentItem = 1
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == sliders.size - 1) {
                    sliderHandler.removeCallbacks(sliderRunnable)
                }
            }
        })

    }

    override fun onResume() {
        super.onResume()
        sliderHandler.postDelayed(sliderRunnable, 2000)
    }

    private val sliderRunnable = Runnable {
        viewPager2.currentItem += 1
    }

    companion object {
        private const val MOVIES = "MOVIES"

        @JvmStatic
        fun newInstance(movies: ArrayList<Movie>) =
            MoviesSliderFragment().apply {

            }
    }
}