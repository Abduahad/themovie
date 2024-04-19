package com.freecast.thatmovieapp.data.mapper

import com.freecast.thatmovieapp.data.model.MovieVideoResult
import com.freecast.thatmovieapp.domain.model.MovieVideo

class MovieVideoEntityMapper : (MovieVideoResult) -> List<MovieVideo> {
    override fun invoke(result: MovieVideoResult): List<MovieVideo> {
        return result.results.map {
            MovieVideo(
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