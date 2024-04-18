package com.freecast.thatmovieapp.slider

import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.freecast.thatmovieapp.data.remote.MovieClient
import com.freecast.thatmovieapp.data.repository.MovieRepositoryImpl
import com.freecast.thatmovieapp.domain.usecase.GetMoviesUseCase
import com.freecast.thatmovieapp.movies.MoviesViewModel

class SliderViewModel(endpoint: String, title: String) : MoviesViewModel(endpoint, title) {
    private val useCase: GetMoviesUseCase = GetMoviesUseCase(MovieRepositoryImpl(MovieClient.apiService))
    fun getPageTransformer(): CompositePageTransformer {
        return CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
            addTransformer(ViewPager2.PageTransformer() { page, position ->
                val r = 1 - Math.abs(position)
                page.scaleY = 0.85f + r * 0.15f
            })
        }
    }
}