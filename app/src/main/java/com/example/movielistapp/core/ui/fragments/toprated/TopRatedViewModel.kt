package com.example.movielistapp.core.ui.fragments.toprated

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movielistapp.core.ui.uiState.MovieState
import com.example.movielistapp.data.Result
import com.example.movielistapp.domain.usecases.GetTopRatedMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TopRatedViewModel @Inject constructor(private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase) : ViewModel() {
    private val mutableMovieState: MutableStateFlow<MovieState> =
        MutableStateFlow(MovieState.Loading(true))
    val _mutableMovieState: StateFlow<MovieState> =
        mutableMovieState.asStateFlow()
    fun getTopMovies() {
        try {
            viewModelScope.launch {
                val popularListFlow = withContext(Dispatchers.IO) {
                    getTopRatedMoviesUseCase()
                }
                popularListFlow.collect { topRatedMovieResult ->
                    when (topRatedMovieResult) {
                        is Result.Success -> {
                            val movies = topRatedMovieResult.data

                            if(movies.isEmpty())
                                mutableMovieState.value = MovieState.Empty(true)
                            else
                                mutableMovieState.value = MovieState.Success(movies)
                            mutableMovieState.value = MovieState.Loading(false)
                        }

                        is Result.StrError -> {
                            mutableMovieState.value = MovieState.Error(topRatedMovieResult.errorMsg)
                        }

                        is Result.ResError -> {
                            mutableMovieState.value = MovieState.ResError(topRatedMovieResult.errorMsg)

                        }
                    }
                }
            }
        } catch (ex: Exception) {
            mutableMovieState.value = MovieState.Error(ex.message.toString())

            mutableMovieState.value = MovieState.Loading(false)
        }
    }


}