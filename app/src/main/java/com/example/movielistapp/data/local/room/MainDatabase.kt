package com.example.movielistapp.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movielistapp.data.local.room.doa.MovieDao
import com.example.movielistapp.data.local.room.entity.NowPlayingMovieEntity
import com.example.movielistapp.data.local.room.entity.PopularMovieEntity
import com.example.movielistapp.data.local.room.entity.TopRatedMovieEntity
import com.example.movielistapp.data.local.room.entity.UpcomingMovieEntity


@Database(
    entities = [PopularMovieEntity::class, TopRatedMovieEntity::class, NowPlayingMovieEntity::class, UpcomingMovieEntity::class],
    version = 1
)
abstract class MainDatabase() : RoomDatabase() {
    abstract fun movieDao(): MovieDao

}