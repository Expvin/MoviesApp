package com.expv1n.myfilmsapp.domain.mapper

import com.expv1n.myfilmsapp.domain.models.Country
import com.expv1n.myfilmsapp.domain.models.Film
import com.expv1n.myfilmsapp.domain.models.FilmDetail
import com.expv1n.myfilmsapp.domain.models.Genre
import com.expv1n.myfilmsapp.domain.models.MovieEntity
import java.lang.StringBuilder

class MoviesMapper {

    fun mapModelDtoToEntity(respone: FilmDetail): MovieEntity {
        return MovieEntity(
            id = 0,
            name = respone.nameRu,
            description = respone.description,
            genre = getGenres(respone.genres),
            country = getCountries(respone.countries),
            posterUrl = respone.posterUrl,
            isFavorite = false
        )
    }

    private fun getCountries(countries: List<Country>): String {
        val result = StringBuilder()
        result.append("Страны: ")
        for ((i, tmp) in countries.withIndex()) {
            result.append(tmp.country)
            if (i + 1 != countries.size) {
                result.append(", ")
            } else {
                result.append(".")
            }
        }
        return result.toString()
    }

    private fun getGenres(genres: List<Genre>): String {
        val result = StringBuilder()
        result.append("Жанры: ")
        for ((i, tmp) in genres.withIndex()) {
            result.append(tmp.genre)
            if (i + 1 != genres.size) {
                result.append(", ")
            } else {
                result.append(".")
            }
        }
        return result.toString()
    }

}