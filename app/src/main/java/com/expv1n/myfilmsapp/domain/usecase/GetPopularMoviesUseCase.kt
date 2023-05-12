package com.expv1n.myfilmsapp.domain.usecase

import androidx.paging.PagingData
import com.expv1n.myfilmsapp.domain.Repository
import com.expv1n.myfilmsapp.domain.models.Film
import kotlinx.coroutines.flow.Flow


class GetPopularMoviesUseCase(val repository: Repository) {

    suspend fun invoke(): Flow<PagingData<Film>> {
        return repository.getPopularMovies()
    }

}