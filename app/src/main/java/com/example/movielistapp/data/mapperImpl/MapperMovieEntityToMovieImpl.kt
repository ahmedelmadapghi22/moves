package com.example.movielistapp.data.mapperImpl

import com.example.movielistapp.data.local.room.entity.BaseMovieEntity
import com.example.movielistapp.domain.mappers.MapperMovieEntityToMovie
import com.example.movielistapp.domain.models.Movie
import javax.inject.Inject

class MapperMovieEntityToMovieImpl @Inject constructor() : MapperMovieEntityToMovie {
    override fun toMovie(movieEntity: BaseMovieEntity): Movie {
        return Movie(
            movieEntity.id,
            movieEntity.posterPath,
            movieEntity.title,
            movieEntity.voteCount,
            movieEntity.overview
        )
    }
}