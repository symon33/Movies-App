package com.example.movies

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide


class FragmentDesc : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view : View = inflater.inflate(R.layout.fragment_desc, container, false)

        val imageView : ImageView = view.findViewById(R.id.imageView2)
        val Base_URL = "https://image.tmdb.org/t/p/w500/"
        Glide.with(imageView.context).load(Base_URL + arguments?.getString("image").toString()).into(imageView)
        Log.d("imagePath", arguments?.getString("image").toString())
       // Toast.makeText(view.context, arguments?.getString("image").toString(), Toast.LENGTH_SHORT).show()

        val tittle : TextView = view.findViewById(R.id.title2)
        val date : TextView = view.findViewById(R.id.date2)
        val body : TextView = view.findViewById(R.id.body2)
        tittle.text = arguments?.getString("heading").toString()
        date.text = arguments?.getString("date").toString()
        body.text = arguments?.getString("description").toString()

        return view

    }
}