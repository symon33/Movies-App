package com.example.movies.apiService

import com.example.movies.models.DataModel
import com.example.movies.models.DataModelItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("/3/movie/popular?api_key=535918754ec05c8f2f1bfa9cb3a9c32b")
    // @GET("/posts")

    fun getData(): Call<DataModel>
}