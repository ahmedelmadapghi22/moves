package com.example.movielistapp.data.local.room.doa

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movielistapp.data.local.room.entity.NowPlayingMovieEntity
import com.example.movielistapp.data.local.room.entity.PopularMovieEntity
import com.example.movielistapp.data.local.room.entity.TopRatedMovieEntity
import com.example.movielistapp.data.local.room.entity.UpcomingMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPopularMovies(movies: List<PopularMovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopRatedMovies(movies: List<TopRatedMovieEntity>)



    @Query("SELECT * FROM popular_movies")
    fun getPopularMovies(): Flow<List<PopularMovieEntity>>

    @Query("SELECT * FROM top_rated_movies")
    fun getTopRatedMovies(): Flow<List<TopRatedMovieEntity>>



    @Query("DELETE FROM popular_movies")
    suspend fun deletePopularMovies()
    @Query("DELETE FROM top_rated_movies")
    suspend fun deleteTopRatedMovies()


}