package com.example.movielistapp.data.local.room.entity

import androidx.room.PrimaryKey

open class BaseMovieEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val posterPath: String,
    val title: String,
    val voteCount: Int,
    val overview: String
) {

}