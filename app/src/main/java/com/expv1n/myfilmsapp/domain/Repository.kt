package com.expv1n.myfilmsapp.domain

import androidx.paging.PagingData
import com.expv1n.myfilmsapp.domain.models.Film
import com.expv1n.myfilmsapp.domain.models.FilmDetail
import com.expv1n.myfilmsapp.domain.models.MovieEntity
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getPopularMovies(): Flow<PagingData<Film>>

    suspend fun getFavoriteMovies(): List<MovieEntity>

    suspend fun getMovie(movieId: Int): MovieEntity

    suspend fun addToFavoriteMovies(movie: MovieEntity)

    suspend fun searchMovies()

    suspend fun getDetailMovie(movieId: Long): MovieEntity

    suspend fun deleteMovie(movie: MovieEntity)
}