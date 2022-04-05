package com.example.mymovielist.models.Genre

import com.google.gson.annotations.SerializedName

data class LlistaGenres (
    @SerializedName("genres") var genres : ArrayList<Genres> = arrayListOf()
)