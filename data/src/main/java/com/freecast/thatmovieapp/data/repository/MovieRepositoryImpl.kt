package com.freecast.thatmovieapp.data.repository


import com.freecast.thatmovieapp.data.mapper.GenreEntityMapper
import com.freecast.thatmovieapp.data.mapper.MovieEntityMapper
import com.freecast.thatmovieapp.domain.model.Genre
import com.freecast.thatmovieapp.domain.model.Movie
import com.freecast.thatmovieapp.data.remote.ApiService
import com.freecast.thatmovieapp.data.remote.SafeApiRequest
import com.freecast.thatmovieapp.domain.repository.MovieRepository
import com.freecast.thatmovieapp.domain.repository.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl(private val apiService: ApiService) : SafeApiRequest(), MovieRepository {
    private val language = "en-US"
    private val defaultPage: Int = 1
    override suspend fun getNowPlayingMovies(): Flow<Resource<List<Movie>>> {
        return flow {
            emit(Resource.Loading(true))
            val resource = sendRequest(
                call = { apiService.getNowPlayingMovies(defaultPage, language) },
                mapper = MovieEntityMapper()
            )
            emit(resource)
        }
    }

    override suspend fun getPopularMovies(): Flow<Resource<List<Movie>>> {
        return flow {
            emit(Resource.Loading(true))
            val resource = sendRequest(
                call = { apiService.getPopularMovies(defaultPage, language) },
                mapper = MovieEntityMapper()
            )
            emit(resource)
        }
    }

    override suspend fun getTopRatedMovies(): Flow<Resource<List<Movie>>> {
        return flow {
            emit(Resource.Loading(true))
            val resource = sendRequest(
                call = { apiService.getTopRatedMovies(defaultPage, language) },
                mapper = MovieEntityMapper()
            )
            emit(resource)
        }
    }

    override suspend fun getUpcomingMovies(): Flow<Resource<List<Movie>>> {
        return flow {
            emit(Resource.Loading(true))
            val resource = sendRequest(
                call = { apiService.getUpcomingMovies(defaultPage, language) },
                mapper = MovieEntityMapper()
            )
            emit(resource)
        }
    }

    override suspend fun getGenres(): Flow<Resource<List<Genre>>> {
        return flow {
            emit(Resource.Loading(true))
            val resource = sendRequest(
                call = { apiService.getGenres(language) },
                mapper = GenreEntityMapper()
            )
            emit(resource)
        }
    }

}