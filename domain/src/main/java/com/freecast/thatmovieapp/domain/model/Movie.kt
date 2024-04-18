package com.freecast.thatmovieapp.domain.model

import java.io.Serializable

data class Movie(
    val backdropPath: String?,
    val posterPath: String?,
    val id: Int,
    val originalTitle: String,
    val overview: String?,
    val title: String,
    val video: Boolean
): Serializable