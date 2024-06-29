package com.example.movielistapp.data.server.datasource

import android.util.Log
import com.example.movielistapp.data.server.apimodels.ImageAPIModel
import com.example.movielistapp.data.server.retrofit.MoviesAPIService
import javax.inject.Inject


class MovieImageNetworkDataSource @Inject constructor(private val moviesAPIService: MoviesAPIService) {

    suspend fun getMovieImage(movieID: Int): List<ImageAPIModel>? {
        Log.d("dapgoooooooo", "MovieImageNetworkDataSource: ${moviesAPIService.getMovieImages(movieID = movieID).body()?.posters}")

        return moviesAPIService.getMovieImages(movieID = movieID).body()?.posters
    }

}