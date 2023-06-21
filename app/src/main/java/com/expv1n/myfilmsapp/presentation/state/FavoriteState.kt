package com.expv1n.myfilmsapp.presentation.state

import com.expv1n.myfilmsapp.domain.models.MovieEntity


sealed class FavoriteState

object FavoriteError: FavoriteState()

object FavoriteProgress: FavoriteState()

class FavoriteResult(val result: List<MovieEntity>): FavoriteState()