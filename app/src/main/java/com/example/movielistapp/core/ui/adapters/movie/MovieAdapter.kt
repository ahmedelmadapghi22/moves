package com.example.movielistapp.core.ui.adapters.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movielistapp.databinding.MovieItemBinding
import com.example.movielistapp.domain.models.Movie

class MovieAdapter() : RecyclerView.Adapter<MovieViewHolder>() {
    private var moviesList: List<Movie> = emptyList()
    private lateinit var setOnClickMovie: SetOnClickMovie
    fun injectSetOnClickMovie(setOnClickMovie: SetOnClickMovie) {
        this.setOnClickMovie = setOnClickMovie
    }

    fun injectMoviesList(moviesList: List<Movie>) {
        this.moviesList = moviesList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val movieItemBinding =
            MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(movieItemBinding, setOnClickMovie)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(moviesList[position])
    }
}