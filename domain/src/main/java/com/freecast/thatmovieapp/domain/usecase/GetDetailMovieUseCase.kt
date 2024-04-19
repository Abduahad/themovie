package com.freecast.thatmovieapp.domain.usecase

import MovieDetail
import com.freecast.thatmovieapp.domain.core.BaseUseCase
import com.freecast.thatmovieapp.domain.repository.MovieRepository
import com.freecast.thatmovieapp.domain.repository.Resource
import kotlinx.coroutines.flow.Flow

class GetDetailMovieUseCase(private val movieRepository: MovieRepository) : BaseUseCase<Flow<Resource<MovieDetail>>, Int>() {
    override suspend fun execute(movieId: Int): Flow<Resource<MovieDetail>> {
        return movieRepository.getMovieDetail(movieId)
    }
}