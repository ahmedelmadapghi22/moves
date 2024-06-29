package com.example.movielistapp.core.ui.fragments.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movielistapp.domain.models.Movie
import com.example.movielistapp.domain.usecases.SearchMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchMoviesUseCase: SearchMoviesUseCase) :
    ViewModel() {

    private val _primaryReleaseYear = MutableLiveData<Int>()
    private val _isAdult = MutableLiveData<Boolean>()

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    val movies: LiveData<PagingData<Movie>> = _primaryReleaseYear.switchMap { year ->
        _isAdult.switchMap { adult ->
            liveData {
                _loading.postValue(true)
                val data =searchMoviesUseCase(year, adult).cachedIn(viewModelScope).asLiveData()
                emitSource(data)
                _loading.postValue(false)
            }
        }
    }

    fun searchMovies(year: Int, isAdult: Boolean) {
        _primaryReleaseYear.value = year
        _isAdult.value = isAdult
    }
}