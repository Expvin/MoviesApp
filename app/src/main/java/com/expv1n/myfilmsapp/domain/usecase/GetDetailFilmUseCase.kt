package com.expv1n.myfilmsapp.domain.usecase

import com.expv1n.myfilmsapp.domain.Repository
import com.expv1n.myfilmsapp.domain.models.FilmDetail

class GetDetailFilmUseCase(val repostiory: Repository) {

    suspend fun invoke(filmId: Long): FilmDetail {
        return repostiory.getDetailFilm(filmId)
    }
}