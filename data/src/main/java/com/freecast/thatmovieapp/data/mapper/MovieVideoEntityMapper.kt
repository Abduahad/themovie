package com.freecast.thatmovieapp.data.mapper

import com.freecast.thatmovieapp.data.model.MovieVideoResult
import com.freecast.thatmovieapp.domain.model.MovieVideoEntity

class MovieVideoEntityMapper : (MovieVideoResult) -> List<MovieVideoEntity> {
    override fun invoke(result: MovieVideoResult): List<MovieVideoEntity> {
        return result.results.map {
            MovieVideoEntity(
                id = it.id,
                key = it.key,
                name = it.name,
                official = it.official,
                site = it.site,
                size = it.size,
                type = it.type
            )
        }
    }
}