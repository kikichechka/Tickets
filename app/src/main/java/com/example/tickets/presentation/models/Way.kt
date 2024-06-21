package com.example.tickets.presentation.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Suppress("DEPRECATED_ANNOTATION")
@Parcelize
class Way(
    val cityWhereFrom: String,
    val cityEditWhere: String
) : Parcelable
