package com.example.mykotlinmoviedbappilcation.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mykotlinmoviedbappilcation.R
import com.example.mykotlinmoviedbappilcation.model.MovieDbRepo
import com.example.mykotlinmoviedbappilcation.model.Result
import kotlinx.android.synthetic.main.recyclerview_item_layout.view.*

class MovieAdapter(private val resultList: List<Result>, private val movieItemDelegate: MovieItemDelegate,
                   private var applicationContext: Context) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){


    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            var movieName: TextView = itemView.movie_name_textview
            var movieReleaseDate: TextView = itemView.movie_release_date_textview
            var movieImage: ImageView = itemView.movie_imageview


    }

    interface MovieItemDelegate{
       fun viewMovieItem(result: Result)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item_layout, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return resultList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.movieName.text = resultList[position].originalTitle
        holder.movieReleaseDate.text = resultList[position].releaseDate

        Glide.with(applicationContext)
            .load("https://image.tmdb.org/t/p/w1280" + resultList[position].posterPath)
            .into(holder.movieImage)

        holder.itemView.setOnClickListener {
            Log.d("TAG_Q", "im in the itemclick")
            movieItemDelegate.viewMovieItem(resultList[position])
        }

    }


}