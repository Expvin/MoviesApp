package com.expv1n.myfilmsapp.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Country(
    val country: String
) : Parcelable