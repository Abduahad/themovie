package com.freecast.thatmovieapp.domain.repository

import MovieDetail
import com.freecast.thatmovieapp.domain.model.Genre
import com.freecast.thatmovieapp.domain.model.Movie
import com.freecast.thatmovieapp.domain.model.MovieVideo
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getGenres(): Flow<Resource<List<Genre>>>
    suspend fun getMovies(endpoint:String): Flow<Resource<List<Movie>>>
    suspend fun getMoviesByGenreId(genreId:Int): Flow<Resource<List<Movie>>>
    suspend fun getMovieDetail(movieId:Int): Flow<Resource<MovieDetail>>
    suspend fun getMovieVideos(movieId:Int): Flow<Resource<List<MovieVideo>>>

}