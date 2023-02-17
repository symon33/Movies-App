package com.example.movies.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.FragmentDesc
import com.example.movies.R
import com.example.movies.models.DataModelItem

class MoviesAdapter(private val context: Context, private val modelItem: List<DataModelItem>) :
    RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {

        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.row_movies, parent, false)

        return MoviesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val currentItem = modelItem[position]
        //holder.imageView.setImageResource(currentItem.image)
        val Base_URL = "https://image.tmdb.org/t/p/w500/"
        Glide.with(context).load(Base_URL + currentItem.image).into(holder.imageView)
        holder.title.text = currentItem.title
        holder.date.text = currentItem.date
        holder.body.text = currentItem.body

        holder.itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {

                val activity = p0!!.context as AppCompatActivity
                val fragmentDesc = FragmentDesc()

                val bundle = Bundle()
                bundle.putString("image", currentItem.image)
                bundle.putString("heading", currentItem.title)
                bundle.putString("date", currentItem.date)
                bundle.putString("description", currentItem.body)

                fragmentDesc.arguments = bundle

                activity.supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_layout, fragmentDesc).addToBackStack(null).commit()

                // Toast.makeText(context, "Fragment ${position}", Toast.LENGTH_SHORT).show()

            }


        })
    }

    override fun getItemCount(): Int {
        return modelItem.size
    }

    inner class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val title: TextView = itemView.findViewById(R.id.title)
        val body: TextView = itemView.findViewById(R.id.body)
        val date: TextView = itemView.findViewById(R.id.date)


    }
}