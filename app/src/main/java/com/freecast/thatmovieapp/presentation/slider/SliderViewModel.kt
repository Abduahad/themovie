package com.freecast.thatmovieapp.presentation.slider

import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.freecast.thatmovieapp.presentation.movies.MoviesViewModel

class SliderViewModel() : MoviesViewModel() {
    fun getPageTransformer(): CompositePageTransformer {
        return CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
            addTransformer { page, position ->
                val r = 1 - Math.abs(position)
                page.scaleY = 0.85f + r * 0.15f
            }
        }
    }
}