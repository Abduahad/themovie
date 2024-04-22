package com.freecast.thatmovieapp.data.remote


import MovieDetailEntity
import MovieDetailEntityMapper
import android.content.Context
import com.freecast.thatmovieapp.data.mapper.GenreEntityMapper
import com.freecast.thatmovieapp.data.mapper.MovieEntityMapper
import com.freecast.thatmovieapp.data.mapper.MovieVideoEntityMapper
import com.freecast.thatmovieapp.domain.model.GenreEntity
import com.freecast.thatmovieapp.domain.model.MovieEntity
import com.freecast.thatmovieapp.domain.model.MovieVideoEntity
import com.freecast.thatmovieapp.domain.model.Resource
import com.freecast.thatmovieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteDataSourceImpl(context: Context,private val apiService: ApiService) : SafeApiRequest(context), MovieRepository {
    private val language = "en-US"

    override suspend fun getGenres(isTv: Boolean): Flow<Resource<List<GenreEntity>>> {
        return flow {
            emit(Resource.Loading())
            val resource = sendRequest(
                call = { apiService.getGenres(getTypePath(isTv), language) },
                mapper = GenreEntityMapper()
            )
            emit(resource)
        }
    }

    override suspend fun getMovies(isTv: Boolean, endpoint: String): Flow<Resource<List<MovieEntity>>> {
        return flow {
            emit(Resource.Loading())
            val resource = sendRequest(
                call = { apiService.getMovies(getTypePath(isTv), endpoint) },
                mapper = MovieEntityMapper()
            )
            emit(resource)
        }
    }

    override suspend fun getByGenreId(genreId: Int, isTv: Boolean): Flow<Resource<List<MovieEntity>>> {
        return flow {
            emit(Resource.Loading())
            val resource = sendRequest(
                call = { apiService.getByGenre(getTypePath(isTv), genreId) },
                mapper = MovieEntityMapper()
            )
            emit(resource)
        }
    }

    override suspend fun getDetail(id: Int, isTv: Boolean): Flow<Resource<MovieDetailEntity>> {
        return flow {
            emit(Resource.Loading())
            val resource = sendRequest(
                call = { apiService.getMovieDetail(getTypePath(isTv), id) },
                mapper = MovieDetailEntityMapper()
            )
            emit(resource)
        }
    }

    override suspend fun getMovieVideos(movieId: Int): Flow<Resource<List<MovieVideoEntity>>> {
        return flow {
            emit(Resource.Loading())
            val resource = sendRequest(
                call = { apiService.getMovieVideos(movieId) },
                mapper = MovieVideoEntityMapper()
            )
            emit(resource)
        }
    }

    fun getTypePath(isTv: Boolean): String {
        return if (isTv) "tv" else "movie"
    }

}