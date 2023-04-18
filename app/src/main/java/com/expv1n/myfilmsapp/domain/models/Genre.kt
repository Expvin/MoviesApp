package com.expv1n.myfilmsapp.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Genre(
    val genre: String
) : Parcelable