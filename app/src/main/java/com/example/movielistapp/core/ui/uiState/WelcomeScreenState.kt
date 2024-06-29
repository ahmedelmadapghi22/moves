package com.example.movielistapp.core.ui.uiState

import com.example.movielistapp.domain.models.Movie

sealed class WelcomeScreenState() {
    data class Loading(var isLoading: Boolean) : WelcomeScreenState()
    data class Popular(var popularMoviesList: List<Movie>) : WelcomeScreenState()
    data class TopRated(var topRatedMoviesList: List<Movie>) : WelcomeScreenState()
    data class CacheTime(var isSaved: Boolean) : WelcomeScreenState()
    data class Error(var errMsg: String) : WelcomeScreenState()
    data class ResError(var errMsg: Int) : WelcomeScreenState()


}
