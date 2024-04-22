package com.freecast.thatmovieapp.data.repository


import MovieDetailEntity
import com.freecast.thatmovieapp.data.remote.RemoteDataSourceImpl
import com.freecast.thatmovieapp.domain.model.GenreEntity
import com.freecast.thatmovieapp.domain.model.MovieEntity
import com.freecast.thatmovieapp.domain.model.MovieVideoEntity
import com.freecast.thatmovieapp.domain.model.Resource
import com.freecast.thatmovieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl(private val remoteDataSource: RemoteDataSourceImpl) : MovieRepository {
    override suspend fun getGenres(): Flow<Resource<List<GenreEntity>>> {
        return remoteDataSource.getGenres()
    }

    override suspend fun getMovies(endpoint: String): Flow<Resource<List<MovieEntity>>> {
        return remoteDataSource.getMovies(endpoint)
    }

    override suspend fun getMoviesByGenreId(genreId: Int): Flow<Resource<List<MovieEntity>>> {
        return remoteDataSource.getMoviesByGenreId(genreId)
    }

    override suspend fun getMovieDetail(movieId: Int): Flow<Resource<MovieDetailEntity>> {
        return remoteDataSource.getMovieDetail(movieId)
    }

    override suspend fun getMovieVideos(movieId: Int): Flow<Resource<List<MovieVideoEntity>>> {
        return remoteDataSource.getMovieVideos(movieId)
    }

}