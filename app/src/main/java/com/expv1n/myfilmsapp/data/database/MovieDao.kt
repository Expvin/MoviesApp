package com.expv1n.myfilmsapp.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.expv1n.myfilmsapp.domain.models.MovieEntity


@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovie(movie: MovieEntity)

    @Query("SELECT * FROM movies")
    suspend fun getMovies(): List<MovieEntity>

    @Query("SELECT * FROM movies WHERE id=:movieId")
    suspend fun getMovie(movieId: Int): MovieEntity

    @Delete
    suspend fun deleteMovie(movie: MovieEntity)

}