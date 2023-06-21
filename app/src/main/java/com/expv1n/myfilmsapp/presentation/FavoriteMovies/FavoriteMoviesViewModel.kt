package com.expv1n.myfilmsapp.presentation.FavoriteMovies

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.expv1n.myfilmsapp.data.RepositoryImpl
import com.expv1n.myfilmsapp.domain.usecase.GetFavoriteMoviesUseCase
import com.expv1n.myfilmsapp.presentation.state.FavoriteError
import com.expv1n.myfilmsapp.presentation.state.FavoriteProgress
import com.expv1n.myfilmsapp.presentation.state.FavoriteResult
import com.expv1n.myfilmsapp.presentation.state.FavoriteState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class FavoriteMoviesViewModel(application: Application):
    AndroidViewModel(application) {

    private val repository =  RepositoryImpl(application)
    private val getFavoriteUseCase = GetFavoriteMoviesUseCase(repository)

    private val _getFavoriteLiveData = MutableLiveData<FavoriteState>()
    val getPopularLiveData: LiveData<FavoriteState>
        get() = _getFavoriteLiveData

    fun getPopularMovies() {
        _getFavoriteLiveData.value = FavoriteProgress
        val deferredResult = viewModelScope.async(Dispatchers.IO)
        { getFavoriteUseCase.invoke() }
        viewModelScope.launch {
            val result = deferredResult.await()
            Log.d("FavoriteMoviesViewModel", result.joinToString())
            if (result != null) {
                _getFavoriteLiveData.value = FavoriteResult(result)
            } else {
                _getFavoriteLiveData.value = FavoriteError
            }
        }
    }

}