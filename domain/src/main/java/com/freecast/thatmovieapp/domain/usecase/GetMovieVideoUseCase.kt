package com.freecast.thatmovieapp.domain.usecase

import com.freecast.thatmovieapp.domain.core.BaseUseCase
import com.freecast.thatmovieapp.domain.model.MovieVideoEntity
import com.freecast.thatmovieapp.domain.model.Resource
import com.freecast.thatmovieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetMovieVideoUseCase(private val movieRepository: MovieRepository) : BaseUseCase<List<MovieVideoEntity>, Int>() {
    override suspend fun execute(movieId: Int): Flow<Resource<List<MovieVideoEntity>>> {
        return movieRepository.getMovieVideos(movieId)
    }
}