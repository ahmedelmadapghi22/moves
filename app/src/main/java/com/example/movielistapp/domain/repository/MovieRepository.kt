package com.example.movielistapp.domain.repository

import androidx.paging.PagingData
import com.example.movielistapp.data.Result
import com.example.movielistapp.data.local.room.entity.PopularMovieEntity
import com.example.movielistapp.data.local.room.entity.TopRatedMovieEntity
import com.example.movielistapp.data.server.apimodels.MovieAPIModel
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getPopularMovies(): Flow<Result<List<PopularMovieEntity>>>
    suspend fun getTopRatedMovies(): Flow<Result<List<TopRatedMovieEntity>>>


    suspend fun searchMovie(releaseYear: Int, isAdult: Boolean): Flow<PagingData<MovieAPIModel>>

    suspend fun deletePopularMovies()
    suspend fun deleteTopRatedMovies()


}