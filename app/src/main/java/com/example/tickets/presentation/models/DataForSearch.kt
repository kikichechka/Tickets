package com.example.tickets.presentation.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.time.LocalDate

@Suppress("DEPRECATED_ANNOTATION")
@Parcelize
class DataForSearch(
    val way: Way,
    val departureDate: LocalDate,
    val countPassenger: Int
) : Parcelable
