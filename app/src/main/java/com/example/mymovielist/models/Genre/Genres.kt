package com.example.mymovielist.models.Genre

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Genres (
    @SerializedName("id"   ) var id   : String?    = null,
    @SerializedName("name" ) var name : String? = null
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Genres> {
        override fun createFromParcel(parcel: Parcel): Genres {
            return Genres(parcel)
        }

        override fun newArray(size: Int): Array<Genres?> {
            return arrayOfNulls(size)
        }
    }
}


