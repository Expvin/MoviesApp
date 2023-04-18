package com.expv1n.myfilmsapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.expv1n.myfilmsapp.data.RepositoryImpl
import com.expv1n.myfilmsapp.domain.models.Film
import com.expv1n.myfilmsapp.domain.usecase.GetPopularMoviesUseCase
import kotlinx.coroutines.launch

class PopularMoviesViewModel(): ViewModel() {

    private val repository = RepositoryImpl()
    private val getPopularMoviesUseCaseUC = GetPopularMoviesUseCase(repository)

    private val _getPopularLiveData = MutableLiveData<List<Film>>()
    val getPopularLiveData: LiveData<List<Film>>
        get() = _getPopularLiveData


    suspend fun getPopularMovies() {
        viewModelScope.launch {
            _getPopularLiveData.postValue(getPopularMoviesUseCaseUC.invoke())
        }
    }
}