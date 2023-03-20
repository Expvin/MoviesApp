package com.expv1n.myfilmsapp.data

import com.expv1n.myfilmsapp.data.api.ApiFactory
import com.expv1n.myfilmsapp.domain.Repository
import com.expv1n.myfilmsapp.domain.models.Film

class RepositoryImpl: Repository {

    val apiService = ApiFactory().apiService
    override suspend fun getPopularMovies(): List<Film> {
        return apiService.getPopularMovies(ApiFactory.TOKEN, 1).films
    }

    override suspend fun getFavoriteMovies() {
        TODO("Not yet implemented")
    }

    override suspend fun addToFavoriteMovies() {
        TODO("Not yet implemented")
    }

    override suspend fun searchMovies() {
        TODO("Not yet implemented")
    }

}