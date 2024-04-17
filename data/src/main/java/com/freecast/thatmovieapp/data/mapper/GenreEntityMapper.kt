package com.freecast.thatmovieapp.data.mapper

import com.freecast.thatmovieapp.domain.model.Genre
import com.freecast.thatmovieapp.data.model.GenreEntity

class GenreEntityMapper : (List<GenreEntity>) -> List<Genre> {
    fun map(genreEntity: GenreEntity): Genre {
        return Genre(
            id = genreEntity.id,
            name = genreEntity.name
        )
    }

    override fun invoke(genres: List<GenreEntity>): List<Genre> {
        return genres.map { map(it) }
    }
}