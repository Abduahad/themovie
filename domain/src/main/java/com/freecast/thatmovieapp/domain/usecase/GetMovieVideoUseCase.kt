package com.freecast.thatmovieapp.domain.usecase

import com.freecast.thatmovieapp.domain.core.BaseUseCase
import com.freecast.thatmovieapp.domain.model.MovieVideo
import com.freecast.thatmovieapp.domain.repository.MovieRepository
import com.freecast.thatmovieapp.domain.repository.Resource
import kotlinx.coroutines.flow.Flow

class GetMovieVideoUseCase(private val movieRepository: MovieRepository) : BaseUseCase<Flow<Resource<List<MovieVideo>>>, Int>() {
    override suspend fun execute(movieId: Int): Flow<Resource<List<MovieVideo>>> {
        return movieRepository.getMovieVideos(movieId)
    }
}