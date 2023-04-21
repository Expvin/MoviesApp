package com.expv1n.myfilmsapp.domain.mapper

import com.expv1n.myfilmsapp.domain.models.Film
import com.expv1n.myfilmsapp.domain.models.MovieEntity

fun modelToEntity(film: Film): MovieEntity {
    return MovieEntity(
        id = 0,
        countries = film.countries,
        filmId = film.filmId,
        filmLength = film.filmLength,
        genres = film.genres,
        nameEn = film.nameEn,
        nameRu = film.nameRu,
        posterUrl = film.posterUrl,
        posterUrlPreview = film.posterUrlPreview,
        rating = film.rating,
        ratingChange = film.ratingChange,
        ratingVoteCount = film.ratingVoteCount,
        year = film.year)
}

fun entityToModel(film: MovieEntity): Film {
    return Film(
        countries = film.countries,
        filmId = film.filmId,
        filmLength = film.filmLength,
        genres = film.genres,
        nameEn = film.nameEn,
        nameRu = film.nameRu,
        posterUrl = film.posterUrl,
        posterUrlPreview = film.posterUrlPreview,
        rating = film.rating,
        ratingChange = film.ratingChange,
        ratingVoteCount = film.ratingVoteCount,
        year = film.year)
}

