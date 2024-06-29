package com.example.movielistapp.core.di.modules

import android.content.Context
import com.example.movielistapp.data.local.dataStore.MainDataStore
import com.example.movielistapp.data.local.room.doa.MovieDao
import com.example.movielistapp.data.mapper.MovieMapper
import com.example.movielistapp.data.repositoryImpl.CacheTimeRepositoryImpl
import com.example.movielistapp.data.repositoryImpl.MovieImagesRepositoryImpl
import com.example.movielistapp.data.repositoryImpl.MovieRepositoryImpl
import com.example.movielistapp.data.server.datasource.MovieImageNetworkDataSource
import com.example.movielistapp.data.server.datasource.MovieNetworkDataSource
import com.example.movielistapp.domain.repository.CacheTimeRepository
import com.example.movielistapp.domain.repository.MovieImagesRepository
import com.example.movielistapp.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideMovieRepository(
        movieDao: MovieDao, movieNetworkDataSource: MovieNetworkDataSource, movieMapper: MovieMapper,@ApplicationContext context: Context
    ): MovieRepository {
        return MovieRepositoryImpl(movieDao, movieNetworkDataSource, movieMapper,context)
    }

    @Provides
    fun provideCacheRepository(
        mainDataStore: MainDataStore
    ): CacheTimeRepository {
        return CacheTimeRepositoryImpl(mainDataStore)
    }

    @Provides
    fun provideMovieImagesRepository(
        movieImageNetworkDataSource: MovieImageNetworkDataSource,@ApplicationContext context: Context
    ): MovieImagesRepository {
        return MovieImagesRepositoryImpl(movieImageNetworkDataSource,context)
    }

}