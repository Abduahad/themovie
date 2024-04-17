package com.freecast.thatmovieapp.data.remote

import com.freecast.thatmovieapp.data.model.GenreEntity
import com.freecast.thatmovieapp.data.model.MovieEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(@Query("page") page:Int, @Query("language") language: String = "en-US"): Response<List<MovieEntity>>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("page") page:Int, @Query("language") language: String = "en-US"): Response<List<MovieEntity>>

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page") page:Int, @Query("language") language: String = "en-US"): Response<List<MovieEntity>>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("page") page:Int, @Query("language") language: String = "en-US"): Response<List<MovieEntity>>

    @GET("genre/movie/list")
    suspend fun getGenres(@Query("language") language: String = "en-US"): Response<List<GenreEntity>>

}