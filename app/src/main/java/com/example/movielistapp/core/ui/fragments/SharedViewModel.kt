package com.example.movielistapp.core.ui.fragments

import androidx.lifecycle.ViewModel
import com.example.movielistapp.domain.models.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor() : ViewModel() {

    private var movie: Movie? = null

    fun setMovie(movie: Movie) {
        this.movie = movie
    }

    fun getMovie() = movie


}