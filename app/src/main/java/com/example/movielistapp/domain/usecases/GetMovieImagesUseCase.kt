package com.example.movielistapp.domain.usecases

import com.example.movielistapp.R
import com.example.movielistapp.data.Result
import com.example.movielistapp.domain.repository.MovieImagesRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieImagesUseCase @Inject constructor(
    private val movieImagesRepository: MovieImagesRepository,
) {
    suspend operator fun invoke(movieID: Int) = flow {
        try {
            if (movieImagesRepository.getMovieImagesById(movieID) != null) {
                emit(Result.Success(movieImagesRepository.getMovieImagesById(movieID)))

            } else {
                emit(Result.ResError(R.string.there_is_no_connection_internet))
                emit(Result.Success(null))

            }
        } catch (ex: Exception) {
            emit(Result.StrError(ex.message.toString()))

        }
    }


}