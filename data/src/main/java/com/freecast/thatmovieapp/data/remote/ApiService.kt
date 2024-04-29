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

    @GET("genre/{typePath}/list")
    suspend fun getGenres(@Path("typePath") typePath: String, @Query("language") language: String = "en-US"): Response<GenreResult>

    @GET("{typePath}/{endpoint}")
    suspend fun getMovies(@Path("typePath") typePath: String, @Path("endpoint") endpoint: String): Response<MovieResult>

    @GET("discover/{typePath}")
    suspend fun getByGenre(@Path("typePath") typePath: String, @Query("with_genres") withGenres: Int): Response<MovieResult>

    @GET("{typePath}/{id}")
    suspend fun getMovieDetail(@Path("typePath") typePath: String, @Path("id") id: Int): Response<MovieDetailModel>

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideos(@Path("movie_id") movieId: Int): Response<MovieVideoResult>
}