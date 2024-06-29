package com.example.movielistapp.data.server.apimodels

import com.google.gson.annotations.SerializedName

data class MovieAPIModel(
    @SerializedName("id") val id: Int,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("title") val title: String,
    @SerializedName("vote_count") val voteCount: Int,
    @SerializedName("overview") val overview: String
) {

}