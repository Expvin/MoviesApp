package com.expv1n.myfilmsapp.domain

import androidx.paging.PagingData
import com.expv1n.myfilmsapp.domain.models.Film
import com.expv1n.myfilmsapp.domain.models.FilmDetail
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getPopularMovies(): Flow<PagingData<Film>>

    suspend fun getFavoriteMovies()

    suspend fun addToFavoriteMovies()

    suspend fun searchMovies()

    suspend fun getDetailFilm(filmId: Long): FilmDetail
}