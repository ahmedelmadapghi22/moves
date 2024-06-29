package com.example.movielistapp.domain.usecases

import com.example.movielistapp.data.Result
import com.example.movielistapp.domain.mappers.MapperMovieEntityToMovie
import com.example.movielistapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTopRatedMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,

    private val mapperMovieEntityToMovie: MapperMovieEntityToMovie
) {
    suspend operator fun invoke() = flow {
        movieRepository.getTopRatedMovies().collect { result ->
            when (result) {
                is Result.Success -> {


                    emit(Result.Success(mapperMovieEntityToMovie.toMoviesList(result.data)))


                }

                is Result.StrError -> {
                    emit(Result.StrError(result.errorMsg))

                }

                is Result.ResError -> {
                    emit(Result.ResError(result.errorMsg))

                }
            }

        }

    }

}