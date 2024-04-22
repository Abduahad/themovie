package com.freecast.thatmovieapp.data.mapper

import com.freecast.thatmovieapp.domain.model.GenreEntity
import com.freecast.thatmovieapp.data.model.GenreModel
import com.freecast.thatmovieapp.data.model.GenreResult

class GenreEntityMapper : (GenreResult) -> List<GenreEntity> {
    fun map(genreModel: GenreModel): GenreEntity {
        return GenreEntity(
            id = genreModel.id,
            name = genreModel.name
        )
    }

    override fun invoke(genres: GenreResult): List<GenreEntity> {
        return genres.genres.map { map(it) }
    }
}