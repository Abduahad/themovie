package com.freecast.thatmovieapp.domain.usecase

import com.freecast.thatmovieapp.domain.core.BaseUseCase
import com.freecast.thatmovieapp.domain.model.Movie
import com.freecast.thatmovieapp.domain.repository.MovieRepository
import com.freecast.thatmovieapp.domain.repository.Resource
import kotlinx.coroutines.flow.Flow

class GetMoviesUseCase(private val movieRepository: MovieRepository) : BaseUseCase<Flow<Resource<List<Movie>>>, String>() {
    override suspend fun execute(endPoint: String): Flow<Resource<List<Movie>>> {
        return movieRepository.getMovies(endPoint)
    }
    suspend fun execute(genreId:Int): Flow<Resource<List<Movie>>> {
        return movieRepository.getMoviesByGenreId(genreId)
    }

}