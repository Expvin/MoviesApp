package com.expv1n.myfilmsapp.domain.usecase

import com.expv1n.myfilmsapp.domain.Repository
import com.expv1n.myfilmsapp.domain.models.MovieEntity

class DeleteFromFavoriteUseCase(val repository: Repository) {
    suspend fun invoke(movie: MovieEntity) {
        repository.deleteMovie(movie)
    }
}