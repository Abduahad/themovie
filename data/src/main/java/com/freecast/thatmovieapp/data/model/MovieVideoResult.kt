package com.freecast.thatmovieapp.data.model

import com.google.gson.annotations.SerializedName

data class MovieVideoResult(
    @SerializedName("results")
    val results: List<MovieVideoEntity>
)