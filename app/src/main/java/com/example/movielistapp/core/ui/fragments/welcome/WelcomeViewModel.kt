package com.example.movielistapp.core.ui.fragments.welcome

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movielistapp.core.ui.uiState.WelcomeScreenState
import com.example.movielistapp.data.Result
import com.example.movielistapp.domain.usecases.GetPopularMoviesUseCase
import com.example.movielistapp.domain.usecases.GetTopRatedMoviesUseCase
import com.example.movielistapp.domain.usecases.cachetime.InValidateDataUseCase
import com.example.movielistapp.domain.usecases.cachetime.SaveCacheTimeUseCase
import com.example.movielistapp.domain.usecases.delete.DeleteDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val popularMoviesUseCase: GetPopularMoviesUseCase,
    private val topRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val saveCacheTimeUseCase: SaveCacheTimeUseCase,
    private val inValidateDataUseCase: InValidateDataUseCase,
    private val deleteDataUseCase: DeleteDataUseCase
) : ViewModel() {
    private val mutableWelcomeScreenState: MutableStateFlow<WelcomeScreenState> =
        MutableStateFlow(WelcomeScreenState.Loading(true))
    val _mutableWelcomeScreenState: StateFlow<WelcomeScreenState> =
        mutableWelcomeScreenState.asStateFlow()

    fun inValidateData() {
        viewModelScope.launch {
            val inValidateDataFlow = withContext(Dispatchers.IO) {
                inValidateDataUseCase()
            }
            inValidateDataFlow.collect { isValidate ->
                if (isValidate == 0L) {
                    Log.d("dapgooo", "No Cache found")
                    getPopularMovie()
                } else {
                    // Delete data from room and get data from api and store them in room
                    Log.d("dapgooo", "Cache Found")

                    deleteDataUseCase()
                    getPopularMovie()


                }
            }

        }
    }


    fun getPopularMovie() {
        try {
            viewModelScope.launch {
                val popularListFlow = withContext(Dispatchers.IO) {
                    popularMoviesUseCase()
                }
                popularListFlow.collect { popularMovieResult ->
                    when (popularMovieResult) {
                        is Result.Success -> {

                            mutableWelcomeScreenState.value =
                                WelcomeScreenState.Popular(popularMovieResult.data)
                        }

                        is Result.StrError -> {
                            mutableWelcomeScreenState.value =
                                WelcomeScreenState.Error(popularMovieResult.errorMsg)
                        }

                        is Result.ResError -> {
                            mutableWelcomeScreenState.value =
                                WelcomeScreenState.ResError(popularMovieResult.errorMsg)
                        }
                    }
                }
            }
        } catch (ex: Exception) {
            mutableWelcomeScreenState.value = WelcomeScreenState.Error(ex.message.toString())
            mutableWelcomeScreenState.value = WelcomeScreenState.Loading(false)
            Log.d("dapgooo", ex.message.toString())

        }
    }

    fun getTopRatedMovie() {
        try {
            viewModelScope.launch {
                val topRatedResultFlow = withContext(Dispatchers.IO) {
                    topRatedMoviesUseCase()
                }
                topRatedResultFlow.collect { popularMovieResult ->
                    when (popularMovieResult) {
                        is Result.Success -> {

                            mutableWelcomeScreenState.value =
                                WelcomeScreenState.TopRated(popularMovieResult.data)
                        }

                        is Result.StrError -> {
                            mutableWelcomeScreenState.value =
                                WelcomeScreenState.Error(popularMovieResult.errorMsg)
                        }

                        is Result.ResError -> {
                            mutableWelcomeScreenState.value =
                                WelcomeScreenState.ResError(popularMovieResult.errorMsg)
                        }
                    }
                }
            }
        } catch (ex: Exception) {
            mutableWelcomeScreenState.value = WelcomeScreenState.Error(ex.message.toString())
            mutableWelcomeScreenState.value = WelcomeScreenState.Loading(false)

        }
    }


    suspend fun saveCacheTime() {
        saveCacheTimeUseCase()
        mutableWelcomeScreenState.value = WelcomeScreenState.CacheTime(true)

    }

}