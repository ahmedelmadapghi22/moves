package com.example.movielistapp.data

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class StrError(var errorMsg: String) : Result<Nothing>()
    data class ResError(var errorMsg: Int) : Result<Nothing>()
}
