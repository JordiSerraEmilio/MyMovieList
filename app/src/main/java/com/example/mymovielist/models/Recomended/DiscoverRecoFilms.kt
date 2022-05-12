package com.example.mymovielist.models.Recomended

import com.google.gson.annotations.SerializedName

class DiscoverRecoFilms(
    @SerializedName("page") var page: Int? = null,
    @SerializedName("results") var results: ArrayList<RecoFilms> = arrayListOf(),
    @SerializedName("total_pages") var totalPages: Int? = null,
    @SerializedName("total_results") var totalResults: Int? = null

)