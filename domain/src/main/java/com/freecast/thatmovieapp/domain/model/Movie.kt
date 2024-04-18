package com.freecast.thatmovieapp.domain.model


data class Movie(
    val backdropPath: String?,
    val posterPath: String?,
    val id: Int,
    val originalTitle: String,
    val overview: String?,
    val title: String,
    val video: Boolean
)