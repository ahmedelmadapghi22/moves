package com.example.movielistapp.data.repositoryImpl

import android.content.Context
import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movielistapp.R
import com.example.movielistapp.data.Result
import com.example.movielistapp.data.Result.ResError
import com.example.movielistapp.data.Result.StrError
import com.example.movielistapp.data.Result.Success
import com.example.movielistapp.data.local.room.doa.MovieDao
import com.example.movielistapp.data.mapper.MovieMapper
import com.example.movielistapp.data.paging.SearchMoviesPagingSource
import com.example.movielistapp.data.server.apimodels.MovieAPIModel
import com.example.movielistapp.data.server.datasource.MovieNetworkDataSource
import com.example.movielistapp.data.util.NetworkUtils
import com.example.movielistapp.domain.repository.MovieRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieDao: MovieDao,
    private val movieNetworkDataSource: MovieNetworkDataSource,
    private val movieMapper: MovieMapper,
    @ApplicationContext private val context: Context
) : MovieRepository {

    override suspend fun getPopularMovies() = flow {
        try {

            movieDao.getPopularMovies().collect { popularMoviesEntity ->
                if (popularMoviesEntity.isEmpty()) {
                    if (NetworkUtils.isInternetAvailable(context)) {
                        emit(ResError(R.string.there_is_no_connection_internet))
                    } else {
                        val result = movieNetworkDataSource.getPopularMovies()
                        when (result) {
                            is Success -> {
                                movieDao.insertPopularMovies(movieMapper.mapPopular(result.data))
                            }

                            is StrError -> {

                                emit(StrError(result.errorMsg))
                            }

                            is ResError -> {
                                emit(ResError(result.errorMsg))


                            }
                        }
                        emit(Success(popularMoviesEntity))
                    }


                } else {

                    emit(Success(popularMoviesEntity))

                }


            }


        } catch (ex: Exception) {
            StrError(ex.message.toString())
        }
    }


    override suspend fun getTopRatedMovies() = flow {
        try {
            movieDao.getTopRatedMovies().collect { topRatedMoviesEntity ->
                if (topRatedMoviesEntity.isEmpty()) {
                    if (NetworkUtils.isInternetAvailable(context)) {
                        emit(ResError(R.string.there_is_no_connection_internet))
                    } else {
                        val result = movieNetworkDataSource.getTopRatedMovies()
                        when (result) {
                            is Success -> {
                                movieDao.insertTopRatedMovies(movieMapper.mapTopRated(result.data))
                            }

                            is StrError -> {

                                emit(StrError(result.errorMsg))
                            }

                            is ResError -> {
                                emit(ResError(result.errorMsg))


                            }
                        }
                        emit(Success(topRatedMoviesEntity))
                    }


                } else {

                    emit(Success(topRatedMoviesEntity))

                }


            }


        } catch (ex: Exception) {
            StrError(ex.message.toString())
        }
    }




    override suspend fun searchMovie(
        releaseYear: Int, isAdult: Boolean
    ): Flow<PagingData<MovieAPIModel>> {
        return Pager(config = PagingConfig(
            pageSize = 20, enablePlaceholders = false
        ), pagingSourceFactory = {
            SearchMoviesPagingSource(
                movieNetworkDataSource, releaseYear, isAdult
            )
        }).flow

    }


    override suspend fun deletePopularMovies() {
        return movieDao.deletePopularMovies()
    }

    override suspend fun deleteTopRatedMovies() {
        return movieDao.deleteTopRatedMovies()
    }


}