package com.freecast.thatmovieapp.detail

import MovieDetail
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.freecast.thatmovieapp.core.ui.BaseViewModel
import com.freecast.thatmovieapp.data.remote.MovieClient
import com.freecast.thatmovieapp.data.remote.exceptions.BaseException
import com.freecast.thatmovieapp.data.repository.MovieRepositoryImpl
import com.freecast.thatmovieapp.domain.repository.Resource
import com.freecast.thatmovieapp.domain.usecase.GetDetailMovieUseCase


open class DetailMovieViewModel : BaseViewModel() {
    private val useCase: GetDetailMovieUseCase = GetDetailMovieUseCase(MovieRepositoryImpl(MovieClient.apiService))
    var movieId: Int = 0
    fun fetchMovieDetail(): LiveData<MovieDetail> {
        val result: MutableLiveData<MovieDetail> = MutableLiveData()
        launchCoroutine {
            useCase.execute(movieId).collect {
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