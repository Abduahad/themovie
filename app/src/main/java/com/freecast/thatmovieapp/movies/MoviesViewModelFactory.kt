package com.freecast.thatmovieapp.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.freecast.thatmovieapp.slider.SliderViewModel

class MoviesViewModelFactory(private val endpoint: String, private val title: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviesViewModel::class.java)) {
            return MoviesViewModel(endpoint, title) as T
        }else if (modelClass.isAssignableFrom(SliderViewModel::class.java)) {
            return SliderViewModel(endpoint, title) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}