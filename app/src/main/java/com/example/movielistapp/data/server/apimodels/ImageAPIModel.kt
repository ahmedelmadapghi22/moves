package com.example.movielistapp.data.server.apimodels

import com.google.gson.annotations.SerializedName

data class ImageAPIModel(
    @SerializedName("file_path") val filePath: String,
) {

}