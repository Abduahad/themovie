package com.freecast.thatmovieapp.data.remote

import com.freecast.thatmovieapp.data.remote.interceptors.APILoggingInterceptor
import com.freecast.thatmovieapp.data.remote.interceptors.ErrorInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieClient {
    private const val BASE_URL = "https://api.themoviedb.org/3/"
    private const val API_KEY =
        "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhMzVmMmMxNTY2YzZmMWU2OWM1MWVlZmIxNzkxOTkzOCIsInN1YiI6IjY2MWQ3NTZlZmQ0YTk2MDE4NjZjNjMyMyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.8qJvqhvn0SdU-Sq9pcs3Jviy__2mE6d9sep-roAHAfU"

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(APILoggingInterceptor()).addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .header("Authorization", "Bearer $API_KEY")
                .method(original.method, original.body)
            val request = requestBuilder.build()
            chain.proceed(request)
        }//.addInterceptor(ErrorInterceptor())
        .build()

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}