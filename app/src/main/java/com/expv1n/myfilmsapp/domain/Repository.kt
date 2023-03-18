package com.expv1n.myfilmsapp.domain

interface Repository {

    suspend fun getPopularMovies()

    suspend fun getFavoriteMovies()

    suspend fun addToFavoriteMovies()

    suspend fun searchMovies()
}