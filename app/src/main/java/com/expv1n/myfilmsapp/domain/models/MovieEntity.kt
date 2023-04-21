package com.expv1n.myfilmsapp.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class MovieEntity (
    @PrimaryKey
    val id: Int,
    val countries: List<Country>,
    val filmId: Int,
    val filmLength: String,
    val genres: List<Genre>,
    val nameEn: String,
    val nameRu: String,
    val posterUrl: String,
    val posterUrlPreview: String,
    val rating: String,
    val ratingChange: String,
    val ratingVoteCount: Int,
    val year: String)