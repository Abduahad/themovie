package com.freecast.thatmovieapp.data.remote


import com.freecast.thatmovieapp.data.model.GenreResult
import com.freecast.thatmovieapp.data.model.MovieDetailModel
import com.freecast.thatmovieapp.data.model.MovieResult
import com.freecast.thatmovieapp.data.model.MovieVideoResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("genre/movie/list")
    suspend fun getGenres(@Query("language") language: String = "en-US"): Response<GenreResult>

    @GET("{endpoint}")
    suspend fun getMovies(@Path("endpoint") endpoint: String): Response<MovieResult>

    @GET("discover/movie")
    suspend fun getMoviesByGenre(@Query("with_genres") withGenres: Int): Response<MovieResult>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") movieId: Int): Response<MovieDetailModel>

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideos(@Path("movie_id") movieId: Int): Response<MovieVideoResult>
}