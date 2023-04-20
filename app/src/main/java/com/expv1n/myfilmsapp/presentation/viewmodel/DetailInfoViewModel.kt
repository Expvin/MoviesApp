package com.expv1n.myfilmsapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.expv1n.myfilmsapp.data.RepositoryImpl
import com.expv1n.myfilmsapp.domain.usecase.GetDetailFilmUseCase
import androidx.lifecycle.viewModelScope
import com.expv1n.myfilmsapp.presentation.state.DetailError
import com.expv1n.myfilmsapp.presentation.state.DetailProgress
import com.expv1n.myfilmsapp.presentation.state.DetailResult
import com.expv1n.myfilmsapp.presentation.state.DetailState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DetailInfoViewModel: ViewModel() {

    private val repository = RepositoryImpl()
    private val getDetailFilm = GetDetailFilmUseCase(repository)

    private val _stateDetail = MutableLiveData<DetailState>()
    val stateDetail: LiveData<DetailState>
        get() = _stateDetail

    fun getDetailInfo(filmId: Long) {
        _stateDetail.value = DetailProgress
        if (filmId.toInt() == -1) {
            _stateDetail.value = DetailError
        } else {
            viewModelScope.launch {
                val deferredResult =
                    viewModelScope.async(Dispatchers.IO) { getDetailFilm.invoke(filmId) }
                delay(5000)
                _stateDetail.value = DetailResult(deferredResult.await())
            }
        }
    }

}