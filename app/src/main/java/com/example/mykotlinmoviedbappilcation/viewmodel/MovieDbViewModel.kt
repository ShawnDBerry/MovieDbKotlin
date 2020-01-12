package com.example.mykotlinmoviedbappilcation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.mykotlinmoviedbappilcation.model.MovieDbRepo
import com.example.mykotlinmoviedbappilcation.network.MovieDbRetrofitInstance
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieDbViewModel(application: Application) : AndroidViewModel(application) {

    val movieDbRetrofit = MovieDbRetrofitInstance()

    fun getMovies(searchMovieString: String): Observable<MovieDbRepo> {
        return movieDbRetrofit.getMovies(searchMovieString)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}