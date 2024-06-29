package com.example.movielistapp.data.repositoryImpl

import android.content.Context
import com.example.movielistapp.data.server.datasource.MovieImageNetworkDataSource
import com.example.movielistapp.data.util.NetworkUtils
import com.example.movielistapp.domain.models.MovieImage
import com.example.movielistapp.domain.repository.MovieImagesRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MovieImagesRepositoryImpl @Inject constructor(
    private val movieImageNetworkDataSource: MovieImageNetworkDataSource,
    @ApplicationContext private val context: Context
) : MovieImagesRepository {
    override suspend fun getMovieImagesById(movieID: Int): List<MovieImage>? {
        return if (NetworkUtils.isInternetAvailable(context)) {
            movieImageNetworkDataSource.getMovieImage(movieID)
                ?.map { imageAPIModel -> MovieImage(imageAPIModel.filePath) }
        } else {
            null
        }

    }

}