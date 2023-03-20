package com.expv1n.myfilmsapp.domain.usecase

import com.expv1n.myfilmsapp.domain.Repository
import com.expv1n.myfilmsapp.domain.models.Film

class GetPopularMovies(val repository: Repository) {

    suspend fun invoke(): List<Film> {
        return repository.getPopularMovies()
    }

}