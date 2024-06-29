package com.example.movielistapp.data.server.apimodels

import com.google.gson.annotations.SerializedName

data class MoviesResult(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val movieAPIModels: List<MovieAPIModel>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)