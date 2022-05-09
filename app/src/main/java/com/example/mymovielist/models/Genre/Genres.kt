package com.example.mymovielist.models.Genre

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
data class Genres(
     var id   : String?    = null,
     var name : String? = null
):Parcelable



