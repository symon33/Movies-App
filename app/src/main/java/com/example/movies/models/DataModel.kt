package com.example.movies.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class DataModel(
    @SerializedName("results")
    val dataModelItem: List<DataModelItem>
): Parcelable{
    constructor():this(mutableListOf())
}