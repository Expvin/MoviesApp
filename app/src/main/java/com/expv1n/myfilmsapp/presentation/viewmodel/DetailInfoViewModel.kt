package com.expv1n.myfilmsapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.expv1n.myfilmsapp.data.RepositoryImpl
import com.expv1n.myfilmsapp.domain.usecase.GetDetailFilmUseCase
import androidx.lifecycle.viewModelScope
import com.expv1n.myfilmsapp.domain.models.FilmDetail
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class DetailInfoViewModel: ViewModel() {

    private val repository = RepositoryImpl()
    private val getDetailFilm = GetDetailFilmUseCase(repository)

    private val _getDetailFilmLiveData = MutableLiveData<FilmDetail>()
    val getDetailFilmLiveData: LiveData<FilmDetail>
        get() = _getDetailFilmLiveData

    fun getDetailInfo(filmId: Long) {
        viewModelScope.launch {
            val defferd = viewModelScope.async { getDetailFilm.invoke(filmId) }
            Log.d("MovieDetailsVM", getDetailFilm.invoke(filmId).toString())
            _getDetailFilmLiveData.value = defferd.await()
        }
    }

}