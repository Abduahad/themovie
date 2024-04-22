package com.freecast.thatmovieapp.core.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freecast.thatmovieapp.core.util.SingleLiveData
import com.freecast.thatmovieapp.data.remote.exceptions.BaseException
import com.freecast.thatmovieapp.domain.model.Resource
import com.freecast.thatmovieapp.domain.repository.MovieRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent

abstract class BaseViewModel() : ViewModel() {
    protected val movieRepository: MovieRepository by KoinJavaComponent.inject(MovieRepository::class.java)
    val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _errorHandler: SingleLiveData<BaseException> = SingleLiveData()
    val errorHandler: LiveData<BaseException> = _errorHandler

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->

    }

    protected fun launchCoroutine(block: suspend () -> Unit) {
        viewModelScope.launch(exceptionHandler) {
            block()
        }
    }

    fun handleError(exception: BaseException) {
        _errorHandler.postValue(exception)
    }

    protected fun <T> fetchData(call: suspend () -> Flow<Resource<T>>): LiveData<T> {
        val result: SingleLiveData<T> = SingleLiveData()
        launchCoroutine {
            call.invoke().collect {
                when (it) {
                    is Resource.Loading -> {
                        _isLoading.postValue(true)
                    }

                    is Resource.Success -> {
                        result.postValue(it.data)
                        _isLoading.postValue(false)
                    }

                    is Resource.Error -> {
                        handleError(it.data as BaseException)
                        _isLoading.postValue(false)
                    }
                }
            }
        }
        return result
    }
}