package com.example.movielistapp.core.ui.uiState

import com.example.movielistapp.domain.models.Movie
import com.example.movielistapp.domain.models.MovieImage

sealed class DetailsMovieState() {
    data class Loading(var isLoading: Boolean) : DetailsMovieState()
    data class Details(var movie: Movie?, var posters: List<MovieImage>?) : DetailsMovieState()
    data class Error(var errMsg: String) : DetailsMovieState()
    data class ResError(var errMsg: Int) : DetailsMovieState()


}
