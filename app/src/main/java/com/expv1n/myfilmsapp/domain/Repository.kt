package com.expv1n.myfilmsapp.domain

import com.expv1n.myfilmsapp.domain.models.Film

interface Repository {

    suspend fun getPopularMovies(): List<Film>

    suspend fun getFavoriteMovies()

    suspend fun addToFavoriteMovies()

    suspend fun searchMovies()
}