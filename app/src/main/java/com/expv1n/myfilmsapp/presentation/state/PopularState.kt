package com.expv1n.myfilmsapp.presentation.state

import androidx.paging.PagingData
import com.expv1n.myfilmsapp.domain.models.Film
import kotlinx.coroutines.flow.Flow

sealed class PopularState

object PopularError: PopularState()

object PopularProgress: PopularState()

class PopularResult(val result: Flow<PagingData<Film>>): PopularState()