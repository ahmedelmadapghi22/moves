package com.example.movielistapp.domain.mappers

import com.example.movielistapp.data.local.room.entity.BaseMovieEntity
import com.example.movielistapp.domain.models.Movie

interface MapperMovieEntityToMovie {
    fun toMovie(movieEntity: BaseMovieEntity): Movie
    fun toMoviesList(movieEntities: List<BaseMovieEntity>): List<Movie> =
        movieEntities.map { toMovie(it) }


}