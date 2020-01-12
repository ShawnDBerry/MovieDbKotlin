package com.example.mykotlinmoviedbappilcation.network

import com.example.mykotlinmoviedbappilcation.model.MovieDbRepo
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MovieDbRetrofitInstance {

    private lateinit var movieDbService: MovieDbService

    private var API_KEY: String = "609f3934d8318546dfe36d68005e4862"

    private val BASE_URL: String = "https://api.themoviedb.org/3/search/movie/"

    init {
        movieDbService = createService(retrofitInstance())
    }

    private fun retrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private fun createService(retrofit: Retrofit): MovieDbService {
        return retrofit.create(MovieDbService::class.java)
    }

    fun getMovies(searchMovieString: String): Observable<MovieDbRepo> {
        return movieDbService.getMovies(API_KEY, searchMovieString)
    }

}