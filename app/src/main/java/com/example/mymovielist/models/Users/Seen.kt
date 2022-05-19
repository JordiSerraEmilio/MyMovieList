package com.example.mymovielist.models.Users

import com.google.gson.annotations.SerializedName

data class Seen (
    @SerializedName("id"            ) var id           : String? = null,
    @SerializedName("movie_id"      ) var movieId      : String? = null,
    @SerializedName("backdrop_path" ) var backdropPath : String? = null,
    @SerializedName("movie_title"   ) var movieTitle   : String? = null
)
