package com.example.movielistapp.domain.models

import android.os.Parcelable

@kotlinx.parcelize.Parcelize
data class Movie(
    val id: Int, val posterPath: String, val title: String, val voteCount: Int, val overview: String
) : Parcelable
