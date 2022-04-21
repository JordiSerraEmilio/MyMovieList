package com.example.mymovielist.models.Users

import com.google.gson.annotations.SerializedName

data class User (

    @SerializedName("_id") val _id : String,
    @SerializedName("name") val name : String,
    @SerializedName("email") val email : String,
    @SerializedName("password") val password : String,
    @SerializedName("salt") val salt : String,
    @SerializedName("genres") val genres : List<String>,
    @SerializedName("reviews") val reviews : List<String>
)
