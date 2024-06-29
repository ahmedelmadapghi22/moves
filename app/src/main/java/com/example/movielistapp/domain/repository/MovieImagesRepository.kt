package com.example.movielistapp.domain.repository

import com.example.movielistapp.domain.models.MovieImage

interface MovieImagesRepository {
    suspend fun getMovieImagesById(movieID: Int): List<MovieImage>?

}