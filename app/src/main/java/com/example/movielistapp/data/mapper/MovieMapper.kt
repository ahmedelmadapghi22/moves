package com.example.movielistapp.data.mapper

import com.example.movielistapp.data.local.room.entity.NowPlayingMovieEntity
import com.example.movielistapp.data.local.room.entity.PopularMovieEntity
import com.example.movielistapp.data.local.room.entity.TopRatedMovieEntity
import com.example.movielistapp.data.local.room.entity.UpcomingMovieEntity
import com.example.movielistapp.data.server.apimodels.MovieAPIModel
import javax.inject.Inject

class MovieMapper @Inject constructor() {


    fun mapPopular(input: List<MovieAPIModel>): List<PopularMovieEntity> {
        return input.map {
            PopularMovieEntity(it.id, it.posterPath, it.title, it.voteCount,it.overview)
        }
    }

    fun mapTopRated(input: List<MovieAPIModel>): List<TopRatedMovieEntity> {
        return input.map {
            TopRatedMovieEntity(it.id, it.posterPath, it.title, it.voteCount,it.overview)
        }
    }



}