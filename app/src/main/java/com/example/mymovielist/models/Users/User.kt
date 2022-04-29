package com.example.mymovielist.models.Users

import com.example.mymovielist.models.Genre.Genres
import com.example.mymovielist.models.Review.Reviews
import com.google.gson.annotations.SerializedName

data class User (

    @SerializedName("_id"      ) var Id       : String?            = null,
    @SerializedName("image"    ) var image    : String?            = null,
    @SerializedName("name"     ) var name     : String?            = null,
    @SerializedName("email"    ) var email    : String?            = null,
    @SerializedName("password" ) var password : String?            = null,
    @SerializedName("salt"     ) var salt     : String?            = null,
    @SerializedName("isLogged" ) var isLogged : Int?               = null,
    @SerializedName("genres"   ) var genres   : ArrayList<Genres>  = arrayListOf(),
    @SerializedName("reviews"  ) var reviews  : ArrayList<Reviews> = arrayListOf(),
    @SerializedName("seen"     ) var seen     : ArrayList<FilmsUser>    = arrayListOf(),
    @SerializedName("toWatch"  ) var toWatch  : ArrayList<FilmsUser> = arrayListOf(),
    @SerializedName("dropped"  ) var dropped  : ArrayList<FilmsUser> = arrayListOf()
)
