package com.expv1n.myfilmsapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.expv1n.myfilmsapp.data.RepositoryImpl
import com.expv1n.myfilmsapp.domain.models.Film
import com.expv1n.myfilmsapp.domain.usecase.GetPopularMoviesUseCase
import com.expv1n.myfilmsapp.presentation.state.PopularError
import com.expv1n.myfilmsapp.presentation.state.PopularProgress
import com.expv1n.myfilmsapp.presentation.state.PopularResult
import com.expv1n.myfilmsapp.presentation.state.PopularState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PopularMoviesViewModel: ViewModel() {

    private val repository = RepositoryImpl()
    private val getPopularMoviesUseCaseUC = GetPopularMoviesUseCase(repository)

    private val _getPopularLiveData = MutableLiveData<PopularState>()
    val getPopularLiveData: LiveData<PopularState>
        get() = _getPopularLiveData

    fun getPopularMovies() {
        _getPopularLiveData.value = PopularProgress
        val deferredResult = viewModelScope.async(Dispatchers.IO)
            { getPopularMoviesUseCaseUC.invoke() }
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