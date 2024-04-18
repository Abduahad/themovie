package com.freecast.thatmovieapp.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.freecast.thatmovieapp.core.ui.BaseViewModel
import com.freecast.thatmovieapp.data.remote.MovieClient
import com.freecast.thatmovieapp.data.remote.exceptions.BaseException
import com.freecast.thatmovieapp.data.repository.MovieRepositoryImpl
import com.freecast.thatmovieapp.domain.model.Genre
import com.freecast.thatmovieapp.domain.repository.Resource
import com.freecast.thatmovieapp.domain.usecase.GetGenresUseCase

class GenresViewModel : BaseViewModel() {
    private val useCase: GetGenresUseCase = GetGenresUseCase(MovieRepositoryImpl(MovieClient.apiService))
    fun fetchGenres(): LiveData<List<Genre>> {
        val result: MutableLiveData<List<Genre>> = MutableLiveData()
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