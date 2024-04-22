package com.freecast.thatmovieapp.presentation.detail

import MovieDetailEntity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.freecast.thatmovieapp.core.ui.BaseViewModel
import com.freecast.thatmovieapp.data.remote.exceptions.BaseException
import com.freecast.thatmovieapp.domain.model.MovieVideoEntity
import com.freecast.thatmovieapp.domain.model.Resource
import com.freecast.thatmovieapp.domain.usecase.GetDetailMovieUseCase
import com.freecast.thatmovieapp.domain.usecase.GetMovieVideoUseCase

open class DetailMovieViewModel(private var movieId: Int = 0) : BaseViewModel() {
    fun fetchMovieDetail(): LiveData<MovieDetailEntity> {
        return fetchData {
            GetDetailMovieUseCase(movieRepository).execute(movieId)
        }
    }

    fun fetchMovieVideo(): LiveData<MovieVideoEntity> {
        val result: MutableLiveData<MovieVideoEntity> = MutableLiveData()
        launchCoroutine {
            val useCase = GetMovieVideoUseCase(movieRepository)
            useCase.execute(movieId).collect { it ->
                when (it) {
                    is Resource.Success -> {
                        it.data?.let { videos ->
                            getVideo(videos)?.let { video ->
                                result.postValue(video)
                            }
                        }
                    }

                    is Resource.Error -> {
                        handleError(it.data as BaseException)
                    }

                    is Resource.Loading -> {
                    }
                }
            }
        }
        return result
    }

    private fun getVideo(videos: List<MovieVideoEntity>, site: String = "YouTube", type: String = "Trailer"): MovieVideoEntity? {
        videos.forEach {
            if (it.site == site && it.type == type) {
                return it
            }
        }
        return null
    }

    fun getSimilarMoviesEndPoint(): String {
        return "movie/$movieId/similar"
    }
}