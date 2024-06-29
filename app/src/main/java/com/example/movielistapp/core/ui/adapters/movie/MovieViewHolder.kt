package com.example.movielistapp.core.ui.adapters.movie

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movielistapp.R
import com.example.movielistapp.core.ui.util.Constants
import com.example.movielistapp.databinding.MovieItemBinding
import com.example.movielistapp.domain.models.Movie

class MovieViewHolder(
    private val movieItemBinding: MovieItemBinding,
    private val setOnClickMovie: SetOnClickMovie
) :
    RecyclerView.ViewHolder(movieItemBinding.root) {

    fun bind(movie: Movie) {
        movieItemBinding.apply {
            Glide.with(root.context)
                .load("${Constants.IMAGE_URL}${movie.posterPath}")
                .placeholder(R.drawable.ic_not_found)
                .into(ivPoster)
            tvTitle.text = movie.title
            root.setOnClickListener {
                setOnClickMovie.onClick(movie)
            }
        }
    }


}