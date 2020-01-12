package com.example.mykotlinmoviedbappilcation.network

import com.example.mykotlinmoviedbappilcation.model.MovieDbRepo
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDbService {

    @GET("/3/search/movie")
    fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("query") searchMovieString: String
    ):
            Observable<MovieDbRepo>
}