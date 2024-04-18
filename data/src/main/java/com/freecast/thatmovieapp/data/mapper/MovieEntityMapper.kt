package com.freecast.thatmovieapp.data.mapper

import com.freecast.thatmovieapp.domain.model.Movie
import com.freecast.thatmovieapp.data.model.MovieEntity
import com.freecast.thatmovieapp.data.model.MovieResult


class MovieEntityMapper : (MovieResult) -> List<Movie> {
    fun map(movieEntity: MovieEntity): Movie {
        return Movie(
            backdropPath = movieEntity.backdropPath,
            id = movieEntity.id,
            originalTitle = movieEntity.originalTitle,
            overview = movieEntity.overview,
            posterPath = movieEntity.posterPath,
            title = movieEntity.title,
            video = movieEntity.video
        )
    }

    override fun invoke(movies: MovieResult): List<Movie> {
        return movies.results.map { map(it) }
    }
}