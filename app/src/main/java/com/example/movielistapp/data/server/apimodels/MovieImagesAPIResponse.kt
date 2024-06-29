package com.example.movielistapp.data.server.apimodels

import com.google.gson.annotations.SerializedName

data class MovieImagesAPIResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("posters") val posters: List<ImageAPIModel>

) {

}