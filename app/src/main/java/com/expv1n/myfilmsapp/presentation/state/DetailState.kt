package com.expv1n.myfilmsapp.presentation.state

import com.expv1n.myfilmsapp.domain.models.MovieEntity

sealed class DetailState

object DetailError: DetailState()
object DetailProgress: DetailState()
class DetailResult(val result: MovieEntity): DetailState()