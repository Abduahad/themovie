package com.freecast.thatmovieapp.domain.usecase

import com.freecast.thatmovieapp.domain.core.BaseUseCase
import com.freecast.thatmovieapp.domain.model.MovieEntity
import com.freecast.thatmovieapp.domain.model.Resource
import com.freecast.thatmovieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetMoviesByEndpointUseCase(private val movieRepository: MovieRepository, private val isTv: Boolean) : BaseUseCase<List<MovieEntity>, String>() {
    override suspend fun execute(endPoint: String): Flow<Resource<List<MovieEntity>>> {
        return movieRepository.getMovies(isTv, endPoint)
    }
}