package com.expv1n.myfilmsapp.presentation.state

import com.expv1n.myfilmsapp.domain.models.FilmDetail

sealed class DetailState

object DetailError: DetailState()
object DetailProgress: DetailState()
class DetailResult(val result: FilmDetail): DetailState()