package com.example.mymovielist.models.Actors

import com.google.gson.annotations.SerializedName

data class ActorsLists(
    @SerializedName("id"   ) var id   : Int?            = null,
    @SerializedName("cast" ) var cast : ArrayList<Cast> = arrayListOf(),
    @SerializedName("crew" ) var crew : ArrayList<Crew> = arrayListOf()
)
