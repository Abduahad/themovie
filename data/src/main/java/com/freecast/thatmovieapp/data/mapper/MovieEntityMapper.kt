package com.freecast.thatmovieapp.data.mapper

import com.freecast.thatmovieapp.domain.model.Movie
import com.freecast.thatmovieapp.data.model.MovieEntity


class MovieEntityMapper : (List<MovieEntity>) -> List<Movie> {
    fun map(movieEntity: MovieEntity): Movie {
        return Movie(
            adult = movieEntity.adult,
            backdropPath = movieEntity.backdropPath,
            genreIds = movieEntity.genreIds,
            id = movieEntity.id,
            originalLanguage = movieEntity.originalLanguage,
            originalTitle = movieEntity.originalTitle,
            overview = movieEntity.overview,
            popularity = movieEntity.popularity,
            posterPath = movieEntity.posterPath,
            releaseDate = movieEntity.releaseDate,
            title = movieEntity.title,
            video = movieEntity.video,
            voteAverage = movieEntity.voteAverage,
            voteCount = movieEntity.voteCount
        )
    }

    override fun invoke(movies: List<MovieEntity>): List<Movie> {
        return movies.map { map(it) }
    }
}