package com.freecast.thatmovieapp.presentation.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.freecast.thatmovieapp.core.ui.BaseViewModel
import com.freecast.thatmovieapp.data.remote.exceptions.BaseException
import com.freecast.thatmovieapp.domain.model.MovieEntity
import com.freecast.thatmovieapp.domain.repository.MovieRepository
import com.freecast.thatmovieapp.domain.repository.Resource
import com.freecast.thatmovieapp.domain.usecase.GetMoviesByEndpointUseCase
import com.freecast.thatmovieapp.domain.usecase.GetMoviesByGenreIdUseCase
import com.freecast.thatmovieapp.util.Constants
import org.koin.java.KoinJavaComponent.inject


open class MoviesViewModel : BaseViewModel() {
    private val movieRepository: MovieRepository by inject(MovieRepository::class.java)
    var endPoint: String = Constants.MoviesEndPoint.POPULAR
    var title: String = ""
    var movieId: Int = 0
    fun fetchMovies(): LiveData<List<MovieEntity>> {
        val result: MutableLiveData<List<MovieEntity>> = MutableLiveData()
        launchCoroutine {

            val response = if (movieId > 0) GetMoviesByEndpointUseCase(movieRepository).execute("movie/${movieId}/similar") else GetMoviesByEndpointUseCase(movieRepository).execute(endPoint)
            response.collect {
                when (it) {
                    is Resource.Loading -> {
                        _isLoading.postValue(it.isLoading)
                    }

                    is Resource.Success -> {
                        result.postValue(it.data)
                    }

                    is Resource.Error -> {
                        handleError(it.data as BaseException)
                    }
                }
            }
        }
        return result
    }

    fun fetchMoviesByGenreId(genreId: Int): LiveData<List<MovieEntity>> {
        val result: MutableLiveData<List<MovieEntity>> = MutableLiveData()
        launchCoroutine {
            GetMoviesByGenreIdUseCase(movieRepository).execute(genreId).collect {
                when (it) {
                    is Resource.Loading -> {
                        _isLoading.postValue(it.isLoading)
                    }

                    is Resource.Success -> {
                        result.postValue(it.data)
                    }

                    is Resource.Error -> {
                        handleError(it.data as BaseException)
                    }
                }
            }
        }
        return result
    }
}