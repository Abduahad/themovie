package com.freecast.thatmovieapp.data.model

import com.google.gson.annotations.SerializedName

data class GenreResult(
    @SerializedName("genres")
    val genres: List<GenreEntity>
)