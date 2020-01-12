package com.example.mykotlinmoviedbappilcation.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.mykotlinmoviedbappilcation.R
import com.example.mykotlinmoviedbappilcation.model.Result
import kotlinx.android.synthetic.main.movie_detail_fragment.view.*

class MovieDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return return inflater.inflate(R.layout.movie_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments
        var movieDetail = bundle?.getParcelable<Result>("movie_details")
        view.detail_movie_name_textview.setText(movieDetail?.originalTitle)
        view.detail_movie_release_date_textview.setText((movieDetail?.releaseDate))
        view.detail_movie_overview_textview.setText(movieDetail?.overview)

        Glide.with(view.context.applicationContext)
            .load("https://image.tmdb.org/t/p/w1280" + movieDetail?.posterPath)
            .into(view.movie_imageview)

        view.close_icon_imageview.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
            Log.d("TAG_X", "WHere you at?")
        }
    }


}