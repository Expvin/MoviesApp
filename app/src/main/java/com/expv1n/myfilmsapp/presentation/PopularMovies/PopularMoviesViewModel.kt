package com.expv1n.myfilmsapp.presentation.PopularMovies

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.expv1n.myfilmsapp.data.RepositoryImpl
import com.expv1n.myfilmsapp.domain.models.Film
import com.expv1n.myfilmsapp.domain.usecase.GetPopularMoviesUseCase
import com.expv1n.myfilmsapp.presentation.state.PopularError
import com.expv1n.myfilmsapp.presentation.state.PopularProgress
import com.expv1n.myfilmsapp.presentation.state.PopularResult
import com.expv1n.myfilmsapp.presentation.state.PopularState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class PopularMoviesViewModel(application: Application): AndroidViewModel(application) {

    private val repository = RepositoryImpl(application)
    private val getPopularMoviesUseCaseUC = GetPopularMoviesUseCase(repository)


    suspend fun getMovies(): Flow<PagingData<Film>> {
        return getPopularMoviesUseCaseUC.invoke()
            .map { pagindData ->
                pagindData.map {
                    it
                }
            }
            .cachedIn(viewModelScope)
    }

    private val _getPopularLiveData = MutableLiveData<PopularState>()
    val getPopularLiveData: LiveData<PopularState>
        get() = _getPopularLiveData

    fun getPopularMovies() {
        _getPopularLiveData.value = PopularProgress
        val deferredResult = viewModelScope.async(Dispatchers.IO)
            { getMovies() }
        viewModelScope.launch {
            val result = deferredResult.await()
            if (result != null) {
                _getPopularLiveData.value = PopularResult(result)
            } else {
                _getPopularLiveData.value = PopularError
            }
        }
    }

}