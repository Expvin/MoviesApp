package com.expv1n.myfilmsapp.data.api

import com.expv1n.myfilmsapp.domain.models.FilmDetail
import com.expv1n.myfilmsapp.domain.models.Movies
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryName

interface ApiService {

    @Headers("Content-Type: application/json")
    @GET("api/v2.2/films/top?type=TOP_250_BEST_FILMS")
    suspend fun getPopularMovies(
        @Header("X-API-KEY") token: String = "d4c76bf0-6450-4e4a-a639-8e68e62771f3",
        @Query("page") page: Int): Movies

    @Headers("Content-Type: application/json")
    @GET("api/v2.2/films/{id}")
    suspend fun getDetailFilm(
        @Header("X-API-KEY") token: String = "d4c76bf0-6450-4e4a-a639-8e68e62771f3",
        @Path("id") id: String): FilmDetail

}