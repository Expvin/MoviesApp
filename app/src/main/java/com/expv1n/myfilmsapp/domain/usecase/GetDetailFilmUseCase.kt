package com.expv1n.myfilmsapp.domain.usecase

import com.expv1n.myfilmsapp.domain.Repository
import com.expv1n.myfilmsapp.domain.models.FilmDetail
import com.expv1n.myfilmsapp.domain.models.MovieEntity

class GetDetailFilmUseCase(val repostiory: Repository) {

    suspend fun invoke(movieId: Long): MovieEntity {
        return repostiory.getDetailMovie(movieId)
    }
}