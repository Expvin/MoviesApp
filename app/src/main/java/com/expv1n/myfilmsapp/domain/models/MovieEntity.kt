package com.expv1n.myfilmsapp.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val genre: String,
    val description: String,
    val country: String,
    val posterUrl: String,
    val year: String,
    val isFavorite: Boolean
)