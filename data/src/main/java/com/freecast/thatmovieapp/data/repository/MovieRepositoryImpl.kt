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
    override suspend fun getGenres(isTv: Boolean): Flow<Resource<List<GenreEntity>>> {
        return remoteDataSource.getGenres(isTv)
    }

    override suspend fun getMovies(isTv: Boolean,endpoint: String): Flow<Resource<List<MovieEntity>>> {
        return remoteDataSource.getMovies(isTv,endpoint)
    }

    override suspend fun getByGenreId(genreId: Int, isTv: Boolean): Flow<Resource<List<MovieEntity>>> {
        return remoteDataSource.getByGenreId(genreId, isTv)
    }

    override suspend fun getDetail(id: Int, isTv: Boolean): Flow<Resource<MovieDetailEntity>> {
        return remoteDataSource.getDetail(id, isTv)
    }

    override suspend fun getMovieVideos(movieId: Int): Flow<Resource<List<MovieVideoEntity>>> {
        return remoteDataSource.getMovieVideos(movieId)
    }

}