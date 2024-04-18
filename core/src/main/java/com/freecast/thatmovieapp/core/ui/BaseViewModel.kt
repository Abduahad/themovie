package com.freecast.thatmovieapp.core.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freecast.thatmovieapp.data.remote.exceptions.BaseException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
    val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _errorHandler: MutableLiveData<BaseException> = MutableLiveData()
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
}