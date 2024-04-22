package com.freecast.thatmovieapp.presentation.genres

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.freecast.thatmovieapp.core.ui.BaseViewModel
import com.freecast.thatmovieapp.data.remote.exceptions.BaseException
import com.freecast.thatmovieapp.domain.model.GenreEntity
import com.freecast.thatmovieapp.domain.repository.MovieRepository
import com.freecast.thatmovieapp.domain.repository.Resource
import com.freecast.thatmovieapp.domain.usecase.GetGenresUseCase
import org.koin.java.KoinJavaComponent

class GenresViewModel : BaseViewModel() {
    private val movieRepository: MovieRepository by KoinJavaComponent.inject(MovieRepository::class.java)
    private val useCase: GetGenresUseCase = GetGenresUseCase(movieRepository)
    fun fetchGenres(): LiveData<List<GenreEntity>> {
        val result: MutableLiveData<List<GenreEntity>> = MutableLiveData()
        launchCoroutine {
            useCase.execute(Unit).collect {
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