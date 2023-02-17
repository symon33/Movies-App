package com.example.movies

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.media.Image
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.adapter.MoviesAdapter
import com.example.movies.apiService.ApiInterface
import com.example.movies.models.DataModel
import com.example.movies.models.DataModelItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.themoviedb.org"
//const val BASE_URL = "https://jsonplaceholder.typicode.com"

class MoviesFragment : Fragment() {

    lateinit var mAdapter: MoviesAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var recyclerView: RecyclerView
    //lateinit var fragment: FrameLayout


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_movies, container, false)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // fragment = view.findViewById<FrameLayout>(R.id.frame_layout)

        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)

        linearLayoutManager = LinearLayoutManager(context)

        recyclerView.layoutManager = linearLayoutManager

        getTheData()
    }

    private fun getTheData() {
        //Create Retrofit Builder Object
        val retrofitBuilder = Retrofit.Builder()
            // Add convetor factory
            .addConverterFactory(GsonConverterFactory.create())
            //Add base URL
            .baseUrl(BASE_URL)
            //Add Build fun
            .build()
            // Use create fun which takes ApiInterface as parameter and take a reference of apiinterface
            .create(ApiInterface::class.java)
        //Now we alredy have a retrofitBuilder object we need to create variable to get data from it
        val retrofitData = retrofitBuilder.getData()
        //getData is the fun from ApiInterface

        // In the val retrofitData adding method enqueue and pass an object callback or click Ctrl+Shift +Space
        retrofitData.enqueue(object : Callback<DataModel> {
            override fun onResponse(
                call: Call<DataModel>,
                response: Response<DataModel>
            ) {
                // create a variable to hold the response
                val responseBody = response.body()!!.dataModelItem
                Log.d("MainActivity", "onSuccess: " + responseBody)
                //Toast.makeText(context, responseBody.toString(), Toast.LENGTH_SHORT).show()

                mAdapter = MoviesAdapter(requireActivity(), responseBody)
                mAdapter.notifyDataSetChanged()
                recyclerView.adapter = mAdapter


                //to print data in the textview
//                val stringBuilder = StringBuilder()
//                for(data in responseBody){
//                    //append the data with stringBuilder
//                    stringBuilder.append(data.title)
//                    stringBuilder.append("\n")
//                }
//                val textId: TextView = findViewById(R.id.textId)
//                textId.text = stringBuilder


            }

            override fun onFailure(call: Call<DataModel>, t: Throwable) {
                Log.d("MainActivity", "onFailure: " + t.message)
            }
        })
    }


}