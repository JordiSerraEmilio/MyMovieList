package com.example.mymovielist.models.Reviews

import com.google.gson.annotations.SerializedName

data class Reviews(

    @SerializedName("id") var id: String? = null,
    @SerializedName("movie_id") var movieId: String? = null,
    @SerializedName("backdrop_path") var backdropPath: String? = null,
    @SerializedName("movie_title") var movieTitle: String? = null,
    @SerializedName("comment") var comment: String? = null,
    @SerializedName("score") var score: Float? = null

)
