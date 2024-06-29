package com.example.movielistapp.domain.usecases.delete

import android.util.Log
import com.example.movielistapp.domain.repository.MovieRepository
import javax.inject.Inject

class DeleteDataUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    suspend operator fun invoke() {
        Log.d("dapgooo", "deletePopularMovies")

        movieRepository.deletePopularMovies()
        Log.d("dapgooo", "deleteTopRatedMovies")

        movieRepository.deleteTopRatedMovies()
        Log.d("dapgooo", "deleteNowPlayingMovies")


    }

}