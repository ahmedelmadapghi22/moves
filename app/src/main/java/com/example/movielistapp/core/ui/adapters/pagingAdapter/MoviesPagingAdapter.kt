package com.example.movielistapp.core.ui.adapters.pagingAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.movielistapp.core.ui.adapters.movie.MovieViewHolder
import com.example.movielistapp.core.ui.adapters.movie.SetOnClickMovie
import com.example.movielistapp.databinding.MovieItemBinding
import com.example.movielistapp.domain.models.Movie

class MoviesPagingAdapter : PagingDataAdapter<Movie, MovieViewHolder>(MovieDiffCallback()) {
    private lateinit var setOnClickMovie: SetOnClickMovie
    fun injectSetOnClickMovie(setOnClickMovie: SetOnClickMovie) {
        this.setOnClickMovie = setOnClickMovie
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding, setOnClickMovie)

    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }

}


class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem == newItem
}
