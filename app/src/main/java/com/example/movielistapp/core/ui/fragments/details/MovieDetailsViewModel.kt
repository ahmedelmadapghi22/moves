package com.example.movielistapp.core.ui.fragments.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movielistapp.core.ui.uiState.DetailsMovieState
import com.example.movielistapp.data.Result
import com.example.movielistapp.domain.models.Movie
import com.example.movielistapp.domain.usecases.GetMovieImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val getMovieImagesUseCase: GetMovieImagesUseCase) :
    ViewModel() {
    private val mutableDetailsMovieState: MutableStateFlow<DetailsMovieState> =
        MutableStateFlow(DetailsMovieState.Loading(true))
    val _mutableDetailsMovieState: StateFlow<DetailsMovieState> =
        mutableDetailsMovieState.asStateFlow()


    fun bindDetails(movie: Movie?) {
        viewModelScope.launch {
            val movieImages = getMovieImagesUseCase(movie?.id ?: 1)
            Log.d("dapgoooooooo", "getMovieImagesUseCase: ${movieImages}")

            delay(500)
            getMovieImagesUseCase(movie?.id ?: 1).collect { result ->
                when (result) {
                    is Result.Success -> {
                        mutableDetailsMovieState.value =
                            DetailsMovieState.Details(movie, result.data)
                    }

                    is Result.StrError -> {
                        mutableDetailsMovieState.value = DetailsMovieState.Error(result.errorMsg)
                        mutableDetailsMovieState.value =
                            DetailsMovieState.Details(movie, null)
                    }

                    is Result.ResError -> {
                        mutableDetailsMovieState.value = DetailsMovieState.ResError(result.errorMsg)

                    }
                }


            }


            mutableDetailsMovieState.value = DetailsMovieState.Loading(false)

        }
    }


}