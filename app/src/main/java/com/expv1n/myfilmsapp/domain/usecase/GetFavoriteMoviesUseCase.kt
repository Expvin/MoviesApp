package com.expv1n.myfilmsapp.domain.usecase

import com.expv1n.myfilmsapp.domain.Repository

class GetFavoriteMoviesUseCase(val repository: Repository) {
    suspend fun invoke() = repository.getFavoriteMovies()
}