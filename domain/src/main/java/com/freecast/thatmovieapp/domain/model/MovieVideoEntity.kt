package com.freecast.thatmovieapp.domain.model

data class MovieVideoEntity(
    val id: String?,
    val key: String,
    val name: String?,
    val official: Boolean,
    val site: String,
    val size: Int,
    val type: String
)