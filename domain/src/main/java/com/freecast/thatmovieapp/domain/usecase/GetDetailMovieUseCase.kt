package com.freecast.thatmovieapp.domain.usecase

import MovieDetailEntity
import com.freecast.thatmovieapp.domain.core.BaseUseCase
import com.freecast.thatmovieapp.domain.repository.MovieRepository
import com.freecast.thatmovieapp.domain.repository.Resource
import kotlinx.coroutines.flow.Flow

class GetDetailMovieUseCase(private val movieRepository: MovieRepository) : BaseUseCase<Flow<Resource<MovieDetailEntity>>, Int>() {
    override suspend fun execute(movieId: Int): Flow<Resource<MovieDetailEntity>> {
        return movieRepository.getMovieDetail(movieId)
    }
}