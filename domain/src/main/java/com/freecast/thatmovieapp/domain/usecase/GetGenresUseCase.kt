package com.freecast.thatmovieapp.domain.usecase

import com.freecast.thatmovieapp.domain.core.BaseUseCase
import com.freecast.thatmovieapp.domain.model.GenreEntity
import com.freecast.thatmovieapp.domain.repository.MovieRepository
import com.freecast.thatmovieapp.domain.repository.Resource
import kotlinx.coroutines.flow.Flow

class GetGenresUseCase(private val movieRepository: MovieRepository) : BaseUseCase<Flow<Resource<List<GenreEntity>>>, Unit>() {
    override suspend fun execute(params: Unit):
            Flow<Resource<List<GenreEntity>>> {
        return movieRepository.getGenres()
    }
}