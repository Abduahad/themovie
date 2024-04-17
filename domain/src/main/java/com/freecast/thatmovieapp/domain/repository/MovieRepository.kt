package com.freecast.thatmovieapp.domain.repository

import com.freecast.thatmovieapp.domain.model.Genre
import com.freecast.thatmovieapp.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getNowPlayingMovies(): Flow<Resource<List<Movie>>>
    suspend fun getPopularMovies(): Flow<Resource<List<Movie>>>
    suspend fun getTopRatedMovies(): Flow<Resource<List<Movie>>>
    suspend fun getUpcomingMovies(): Flow<Resource<List<Movie>>>
    suspend fun getGenres(): Flow<Resource<List<Genre>>>

}