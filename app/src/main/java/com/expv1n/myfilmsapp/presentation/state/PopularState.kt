package com.expv1n.myfilmsapp.presentation.state

import com.expv1n.myfilmsapp.domain.models.Film

sealed class PopularState

object PopularError: PopularState()

object PopularProgress: PopularState()

class PopularResult(val result: List<Film>): PopularState()