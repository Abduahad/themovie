package com.freecast.thatmovieapp.presentation.genres

import androidx.lifecycle.LiveData
import com.freecast.thatmovieapp.core.ui.BaseViewModel
import com.freecast.thatmovieapp.domain.model.GenreEntity
import com.freecast.thatmovieapp.domain.usecase.GetGenresUseCase

class GenresViewModel : BaseViewModel() {

    fun fetchGenres(): LiveData<List<GenreEntity>> {
        return fetchData {
            GetGenresUseCase(movieRepository).execute(Unit)
        }
    }

}