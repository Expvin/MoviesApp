package com.expv1n.myfilmsapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.expv1n.myfilmsapp.data.RepositoryImpl
import com.expv1n.myfilmsapp.domain.models.Film
import com.expv1n.myfilmsapp.domain.usecase.GetDetailFilmUseCase
import androidx.lifecycle.viewModelScope

class DetailInfoViewModel: ViewModel() {

    private val repository = RepositoryImpl()
    private val getDetailFilm = GetDetailFilmUseCase(repository)

    private val _getDetailFilmLiveData = MutableLiveData<Film>()
    private val getDetailFilmLiveData: LiveData<Film>
        get() = _getDetailFilmLiveData

    fun getDetailInfo(filmId: Long) {
        viewModelScope.launch {
            _getDetailFilmLiveData.postValue(getDetailFilm.invoke(filmId))
        }
    }

}