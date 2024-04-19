package com.freecast.thatmovieapp.di

import com.freecast.thatmovieapp.data.remote.MovieClient
import com.freecast.thatmovieapp.data.repository.MovieRepositoryImpl
import com.freecast.thatmovieapp.domain.repository.MovieRepository
import org.koin.dsl.module

val networkModule = module {
    single { MovieClient.apiService }
}

val repositoryModule = module {
    single<MovieRepository> { MovieRepositoryImpl(get()) }
}