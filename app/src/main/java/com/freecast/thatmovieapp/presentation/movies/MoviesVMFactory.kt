package com.freecast.thatmovieapp.presentation.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MoviesVMFactory( val isTv:Boolean=false) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviesViewModel::class.java)) {
            return MoviesViewModel(isTv) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
