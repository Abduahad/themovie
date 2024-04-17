package com.freecast.thatmovieapp.domain.usecase

import com.freecast.thatmovieapp.domain.core.BaseUseCase
import com.freecast.thatmovieapp.domain.model.Movie
import com.freecast.thatmovieapp.domain.repository.MovieRepository
import com.freecast.thatmovieapp.domain.repository.Resource
import kotlinx.coroutines.flow.Flow

class GetUpcomingMoviesUseCase(private val movieRepository: MovieRepository) : BaseUseCase<Flow<Resource<List<Movie>>>, Unit>() {
    override suspend fun execute(params: Unit): Flow<Resource<List<Movie>>> {
        return movieRepository.getUpcomingMovies()
    }

}