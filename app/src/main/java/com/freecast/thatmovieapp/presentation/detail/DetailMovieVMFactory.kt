package com.freecast.thatmovieapp.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DetailMovieVMFactory(private val movieId: Int,val isTv:Boolean=false) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailMovieViewModel::class.java)) {
            return DetailMovieViewModel(movieId,isTv) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
