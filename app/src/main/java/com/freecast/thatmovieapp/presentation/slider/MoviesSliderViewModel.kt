package com.freecast.thatmovieapp.presentation.slider

import com.freecast.thatmovieapp.core.ui.BaseViewModel
import com.freecast.thatmovieapp.data.remote.MovieClient
import com.freecast.thatmovieapp.data.repository.MovieRepositoryImpl
import com.freecast.thatmovieapp.domain.usecase.GetNowPlayingMoviesUseCase

class MoviesSliderViewModel() : BaseViewModel() {
    private val useCase: GetNowPlayingMoviesUseCase = GetNowPlayingMoviesUseCase(MovieRepositoryImpl(MovieClient.apiService))
    fun fetchMovies() {
        launchCoroutine {
            useCase.execute(Unit)
        }
    }
}