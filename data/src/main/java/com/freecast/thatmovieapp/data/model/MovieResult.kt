package com.freecast.thatmovieapp.data.model

import com.google.gson.annotations.SerializedName

data class MovieResult(
    @SerializedName("results")
    val results: List<MovieModel>
)