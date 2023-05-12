package com.expv1n.myfilmsapp.data


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.expv1n.myfilmsapp.data.api.ApiFactory
import com.expv1n.myfilmsapp.domain.Paging.MoviesPagingSource
import com.expv1n.myfilmsapp.domain.Repository
import com.expv1n.myfilmsapp.domain.models.Film
import com.expv1n.myfilmsapp.domain.models.FilmDetail
import kotlinx.coroutines.flow.Flow

class RepositoryImpl: Repository {

    val apiService = ApiFactory().apiService
    override suspend fun getPopularMovies(): Flow<PagingData<Film>> {
//        val result = apiService.getPopularMovies(page = 1).pagesCount
//        return apiService.getPopularMovies(page = 1).films
        return Pager(
            config = PagingConfig(
                pageSize = MoviesPagingSource.NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                MoviesPagingSource(service = ApiFactory())
            }
        ).flow
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