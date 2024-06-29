package com.example.movielistapp.core.ui.uiState

import com.example.movielistapp.domain.models.Movie

sealed class MovieState() {
    data class Loading(var isLoading: Boolean) : MovieState()
    data class Empty(var isEmpty: Boolean) : MovieState()
    data class Success(var moviesList: List<Movie>) : MovieState()
    data class Error(var errMsg: String) : MovieState()
    data class ResError(var errMsg: Int) : MovieState()


}
