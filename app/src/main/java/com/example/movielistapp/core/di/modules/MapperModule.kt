package com.example.movielistapp.core.di.modules

import com.example.movielistapp.data.mapperImpl.MapperMovieEntityToMovieImpl
import com.example.movielistapp.domain.mappers.MapperMovieEntityToMovie
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class MapperModule {

    @Provides
    fun provideMapperEntityMovie(
    ): MapperMovieEntityToMovie {
        return MapperMovieEntityToMovieImpl()
    }


}