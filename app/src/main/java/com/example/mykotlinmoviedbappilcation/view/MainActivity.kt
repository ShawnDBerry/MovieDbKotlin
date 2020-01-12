package com.example.mykotlinmoviedbappilcation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.mykotlinmoviedbappilcation.R
import com.example.mykotlinmoviedbappilcation.adapter.MovieAdapter
import com.example.mykotlinmoviedbappilcation.model.Result
import com.example.mykotlinmoviedbappilcation.viewmodel.MovieDbViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.Result as Result1

class MainActivity : AppCompatActivity(), MovieAdapter.MovieItemDelegate {

    val compositeDisposable = CompositeDisposable()

    lateinit var viewModel: MovieDbViewModel

    lateinit var movieDetailFragment: MovieDetailFragment

    private var myAdapter: MovieAdapter? = null

    private var layoutManager: RecyclerView.LayoutManager? = null



    private var DETAILS: String = "movie_details"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        viewModel = ViewModelProviders.of(this).get(MovieDbViewModel::class.java)

        movie_search_button.setOnClickListener {

            Log.d("TAG_Q", "im in here button click")
            compositeDisposable.add(
                viewModel
                    .getMovies(movie_search_editview.text.toString())
                    .subscribe({ movieList ->

                        printResults(movieList.results)
                        setUpRV(movieList.results)

                    }, { throwable ->
                        Log.d("TAG_Q", "error " + throwable.message)
                    })
            )

        }

    }

    private fun printResults(movieList: List<Result>) {
        movieList.forEach { listItem ->
            Log.d("TAG_Q", " " + listItem.originalTitle)
        }
    }

    private fun setUpRV(results: List<Result>) {
        myAdapter = MovieAdapter(results, this, this)
        layoutManager = LinearLayoutManager(this)
        movie_search_recyclerview.adapter = myAdapter
        movie_search_recyclerview.layoutManager = layoutManager


    }

    override fun viewMovieItem(result: Result) {
        Log.d("TAG_Q", "im in the Mainactivity")

        val bundle = Bundle()
        bundle .putParcelable(DETAILS, result)

        movieDetailFragment = MovieDetailFragment()
        movieDetailFragment.arguments = bundle

        supportFragmentManager.beginTransaction()
        .add(R.id.movie_detail_frame_layout, movieDetailFragment)
        .addToBackStack(movieDetailFragment.tag)
        .commit()


    }

}
