package com.expv1n.myfilmsapp.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val genre: String,
    val description: String,
    val country: String,
    val posterUrl: String,
    val isFavorite: Boolean
)