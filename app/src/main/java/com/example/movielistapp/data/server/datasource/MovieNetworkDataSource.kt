package com.example.movielistapp.data.server.datasource

import android.util.Log
import com.example.movielistapp.data.Result
import com.example.movielistapp.data.server.apimodels.MovieAPIModel
import com.example.movielistapp.data.server.apimodels.MoviesResult
import com.example.movielistapp.data.server.retrofit.MoviesAPIService
import retrofit2.Response
import javax.inject.Inject


class MovieNetworkDataSource @Inject constructor(private val moviesAPIService: MoviesAPIService) {


    suspend fun getPopularMovies(): Result<List<MovieAPIModel>> {
        return try {
            val response = moviesAPIService.getPopularMovies()
            if (response.isSuccessful) {
                val moviesResult = response.body()
                if (moviesResult?.movieAPIModels != null) {
                    Log.d(
                        "MovieNetworkDataSource",
                        "Fetched movies: ${moviesResult.movieAPIModels.size}"
                    )
                    Result.Success(moviesResult.movieAPIModels)
                } else {
                    Log.e("MovieNetworkDataSource", "Response body is null or contains no movies")
                    Result.StrError("Response body is null or contains no movies")
                }
            } else {

                Result.StrError("Network call failed with error: ${response.code()}")
            }
        } catch (e: Exception) {
            Log.e("MovieNetworkDataSource", "Exception occurred: ${e.message}", e)
            Result.StrError(e.message.toString())
        }
    }

    suspend fun getTopRatedMovies(): Result<List<MovieAPIModel>> {
        return try {
            val response = moviesAPIService.getTopRatedMovies()
            if (response.isSuccessful) {
                val moviesResult = response.body()
                if (moviesResult?.movieAPIModels != null) {
                    Log.d(
                        "MovieNetworkDataSource",
                        "Fetched movies: ${moviesResult.movieAPIModels.size}"
                    )
                    Result.Success(moviesResult.movieAPIModels)
                } else {
                    Result.StrError("Response body is null or contains no movies")
                }
            } else {

                Result.StrError("Network call failed with error: ${response.code()}")
            }
        } catch (e: Exception) {
            Log.e("MovieNetworkDataSource", "Exception occurred: ${e.message}", e)
            Result.StrError(e.message.toString())
        }
    }

    suspend fun searchMovie(
        releaseYear: Int, isAdult: Boolean, page: Int = 1
    ): Result<MoviesResult?> {
        return try {
            Result.Success(
                moviesAPIService.searchMovie(
                    primaryReleaseYear = releaseYear, isAdult = isAdult, page = page
                ).body()
            )
        } catch (ex: Exception) {
            Result.StrError(ex.message.toString())
        }
    }

    suspend fun searchMovies(
        releaseYear: Int, isAdult: Boolean, page: Int = 1
    ): Response<MoviesResult> {
        return moviesAPIService.searchMovie(
            primaryReleaseYear = releaseYear, isAdult = isAdult, page = page
        )
    }
}