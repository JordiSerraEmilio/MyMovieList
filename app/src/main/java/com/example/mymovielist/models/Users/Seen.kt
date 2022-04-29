package com.example.mymovielist.models.Users

import com.google.gson.annotations.SerializedName

data class FilmsUser (
    @SerializedName("id"       ) var id      : String? = null,
    @SerializedName("movie_id" ) var movieId : String? = null
)
