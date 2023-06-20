package com.expv1n.myfilmsapp.data


import android.app.Application
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.expv1n.myfilmsapp.data.api.ApiFactory
import com.expv1n.myfilmsapp.data.database.MovieDatabase
import com.expv1n.myfilmsapp.domain.Paging.MoviesPagingSource
import com.expv1n.myfilmsapp.domain.Repository
import com.expv1n.myfilmsapp.domain.mapper.MoviesMapper
import com.expv1n.myfilmsapp.domain.models.Film
import com.expv1n.myfilmsapp.domain.models.FilmDetail
import com.expv1n.myfilmsapp.domain.models.MovieEntity
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(application: Application): Repository {

    val apiService = ApiFactory().apiService

    val database = MovieDatabase.getInstance(application)

    val mapper = MoviesMapper()

    override suspend fun getPopularMovies(): Flow<PagingData<Film>> {
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

    override suspend fun getFavoriteMovies(): List<MovieEntity> {
        return database.getDao().getMovies()
    }

    override suspend fun getMovie(movieId: Int): MovieEntity {
        return database.getDao().getMovie(movieId)
    }

    override suspend fun addToFavoriteMovies(movie: MovieEntity) {
        database.getDao().addMovie(movie.copy(isFavorite = true))
    }

    override suspend fun searchMovies() {
        TODO("Not yet implemented")
    }

    override suspend fun getDetailMovie(movieId: Long): MovieEntity {
        val response = apiService.getDetailFilm(id = movieId.toString())
        return mapper.mapModelDtoToEntity(response)
    }

    override suspend fun deleteMovie(movie: MovieEntity) {
        database.getDao().deleteMovie(movie.copy(isFavorite = false))
    }

}