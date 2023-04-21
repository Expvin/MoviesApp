package com.expv1n.myfilmsapp.data


import com.expv1n.myfilmsapp.data.api.ApiFactory
import com.expv1n.myfilmsapp.domain.Repository
import com.expv1n.myfilmsapp.domain.models.Film
import com.expv1n.myfilmsapp.domain.models.FilmDetail

class RepositoryImpl: Repository {

    val apiService = ApiFactory().apiService
    override suspend fun getPopularMovies(): List<Film> {
        val result = apiService.getPopularMovies(page = 1).pagesCount
        return apiService.getPopularMovies(page = 1).films
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

    override suspend fun getDetailFilm(filmId: Long): FilmDetail {
        return apiService.getDetailFilm(id = filmId.toString())
    }

}