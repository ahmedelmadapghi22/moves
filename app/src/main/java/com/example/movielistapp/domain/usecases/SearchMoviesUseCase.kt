package com.example.movielistapp.domain.usecases

import androidx.paging.map
import com.example.movielistapp.domain.models.Movie
import com.example.movielistapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(releaseYear: Int, isAdult: Boolean) = flow {
        movieRepository.searchMovie(releaseYear, isAdult).collect {

            emit(it.map { Movie(it.id, it.posterPath, it.title, it.voteCount,it.overview) })
        }
    }

}