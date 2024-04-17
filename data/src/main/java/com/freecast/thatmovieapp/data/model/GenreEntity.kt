package com.freecast.thatmovieapp.data.model

import com.google.gson.annotations.SerializedName

data class GenreEntity(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)