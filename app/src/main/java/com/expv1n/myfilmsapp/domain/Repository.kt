package com.expv1n.myfilmsapp.domain

import com.expv1n.myfilmsapp.domain.models.Film
import com.expv1n.myfilmsapp.domain.models.FilmDetail

interface Repository {

    suspend fun getPopularMovies(): List<Film>

    suspend fun getFavoriteMovies()

    suspend fun addToFavoriteMovies()

    suspend fun searchMovies()

    suspend fun getDetailFilm(filmId: Long): FilmDetail
}