package com.freecast.thatmovieapp.domain.repository

import MovieDetailEntity
import com.freecast.thatmovieapp.domain.model.GenreEntity
import com.freecast.thatmovieapp.domain.model.MovieEntity
import com.freecast.thatmovieapp.domain.model.MovieVideoEntity
import com.freecast.thatmovieapp.domain.model.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getGenres(): Flow<Resource<List<GenreEntity>>>
    suspend fun getMovies(endpoint:String): Flow<Resource<List<MovieEntity>>>
    suspend fun getMoviesByGenreId(genreId:Int): Flow<Resource<List<MovieEntity>>>
    suspend fun getMovieDetail(movieId:Int): Flow<Resource<MovieDetailEntity>>
    suspend fun getMovieVideos(movieId:Int): Flow<Resource<List<MovieVideoEntity>>>

}