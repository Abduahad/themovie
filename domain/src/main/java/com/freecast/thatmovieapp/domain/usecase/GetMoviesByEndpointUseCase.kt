package com.freecast.thatmovieapp.domain.usecase

import com.freecast.thatmovieapp.domain.core.BaseUseCase
import com.freecast.thatmovieapp.domain.model.MovieEntity
import com.freecast.thatmovieapp.domain.repository.MovieRepository
import com.freecast.thatmovieapp.domain.repository.Resource
import kotlinx.coroutines.flow.Flow

class GetMoviesByEndpointUseCase(private val movieRepository: MovieRepository) : BaseUseCase<Flow<Resource<List<MovieEntity>>>, String>() {
    override suspend fun execute(endPoint: String): Flow<Resource<List<MovieEntity>>> {
        return movieRepository.getMovies(endPoint)
    }
}