package com.example.movielistapp.core.di.modules

import android.content.Context
import androidx.room.Room
import com.example.movielistapp.data.local.room.MainDatabase
import com.example.movielistapp.data.local.room.doa.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
private const val ROOM_NAME = "movies_database"

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context): MainDatabase {
        return Room.databaseBuilder(
            context,
            MainDatabase::class.java,
            ROOM_NAME
        ).build()
    }
    @Provides
    fun provideMovieDao(database: MainDatabase): MovieDao {
        return database.movieDao()
    }




}