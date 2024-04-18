package com.freecast.thatmovieapp.data.mapper

import com.freecast.thatmovieapp.domain.model.Genre
import com.freecast.thatmovieapp.data.model.GenreEntity
import com.freecast.thatmovieapp.data.model.GenreResult

class GenreEntityMapper : (GenreResult) -> List<Genre> {
    fun map(genreEntity: GenreEntity): Genre {
        return Genre(
            id = genreEntity.id,
            name = genreEntity.name
        )
    }

    override fun invoke(genres: GenreResult): List<Genre> {
        return genres.genres.map { map(it) }
    }
}