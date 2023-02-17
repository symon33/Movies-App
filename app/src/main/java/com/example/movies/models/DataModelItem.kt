package com.example.movies.models

import android.media.Image
import android.os.Parcelable
import androidx.versionedparcelable.VersionedParcelize
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataModelItem(
    @SerializedName("poster_path")
    val image: String?,
    @SerializedName("overview")
    val body: String?,
    @SerializedName("original_title")
    val title: String?,
    @SerializedName("release_date")
    val date : String,
    val date_header : String,
    val header : String,
    val movie : String


    //val body: String,
    //val id: Int,
    // val title: String,
    //al userId: Int
): Parcelable{
    constructor(): this("", "", "","", "", "", "")
}