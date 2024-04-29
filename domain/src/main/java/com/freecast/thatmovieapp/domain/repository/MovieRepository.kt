package com.freecast.thatmovieapp.domain.repository

import MovieDetailEntity
import com.freecast.thatmovieapp.domain.model.GenreEntity
import com.freecast.thatmovieapp.domain.model.MovieEntity
import com.freecast.thatmovieapp.domain.model.MovieVideoEntity
import com.freecast.thatmovieapp.domain.model.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getGenres(isTv:Boolean=false): Flow<Resource<List<GenreEntity>>>
    suspend fun getMovies(isTv:Boolean=false,endpoint:String): Flow<Resource<List<MovieEntity>>>
    suspend fun getByGenreId(genreId:Int,isTv:Boolean=false): Flow<Resource<List<MovieEntity>>>
    suspend fun getDetail(id:Int,isTv:Boolean=false): Flow<Resource<MovieDetailEntity>>
    suspend fun getMovieVideos(movieId:Int): Flow<Resource<List<MovieVideoEntity>>>

}