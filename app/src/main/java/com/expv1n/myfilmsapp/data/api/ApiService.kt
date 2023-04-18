package com.expv1n.myfilmsapp.data.api

import com.expv1n.myfilmsapp.domain.models.Movies
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @Headers("Content-Type: application/json")
    @GET("api/v2.2/films/top?type=TOP_250_BEST_FILMS")
    suspend fun getPopularMovies(
        @Header("X-API-KEY") token: String,
        @Query("page") page: Int): Movies


    //Response film detail
    ///api/v2.2/films/top/<id фильма>
}