package com.freecast.thatmovieapp.presentation.movies

import androidx.lifecycle.LiveData
import com.freecast.thatmovieapp.core.ui.BaseViewModel
import com.freecast.thatmovieapp.domain.model.MovieEntity
import com.freecast.thatmovieapp.domain.usecase.GetMoviesByEndpointUseCase
import com.freecast.thatmovieapp.domain.usecase.GetMoviesByGenreIdUseCase


open class MoviesViewModel : BaseViewModel() {
    fun fetchMovies(endPoint: String): LiveData<List<MovieEntity>> {
        return fetchData {
            GetMoviesByEndpointUseCase(movieRepository).execute(endPoint)
        }
    }

    fun fetchMoviesByGenreId(genreId: Int): LiveData<List<MovieEntity>> {
        return fetchData {
            GetMoviesByGenreIdUseCase(movieRepository).execute(genreId)
        }
    }
}