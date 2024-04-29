package com.freecast.thatmovieapp.presentation.genres

import androidx.lifecycle.LiveData
import com.freecast.thatmovieapp.core.ui.BaseViewModel
import com.freecast.thatmovieapp.domain.model.GenreEntity
import com.freecast.thatmovieapp.domain.usecase.GetGenresUseCase
import com.freecast.thatmovieapp.presentation.movies.OnLoadMoviesListener

class GenresViewModel() : BaseViewModel() {
    private var isTv = false
    fun fetchGenres(): LiveData<List<GenreEntity>> {
        return fetchData {
            GetGenresUseCase(movieRepository, isTv).execute(Unit)
        }
    }

    fun setTv(isTv: Boolean) {
        this.isTv = isTv
    }

    fun getTv(): Boolean {
        return isTv
    }

}