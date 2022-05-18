package com.example.mymovielist.models.Genre

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

data class Genres(
     @SerializedName("id") var id   : String?    = null,
     @SerializedName("name") var name : String? = null
)



