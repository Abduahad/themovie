package com.freecast.thatmovieapp.domain.usecase

import com.freecast.thatmovieapp.domain.core.BaseUseCase
import com.freecast.thatmovieapp.domain.model.MovieEntity
import com.freecast.thatmovieapp.domain.model.Resource
import com.freecast.thatmovieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetMoviesByGenreIdUseCase(private val movieRepository: MovieRepository) : BaseUseCase<List<MovieEntity>, Int>() {
    override suspend fun execute(genreId:Int): Flow<Resource<List<MovieEntity>>> {
        return movieRepository.getMoviesByGenreId(genreId)
    }

}