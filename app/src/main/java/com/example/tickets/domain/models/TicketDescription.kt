package com.example.tickets.domain.models

import com.google.gson.annotations.SerializedName

data class TicketDescription(
    val id: Int,
    val badge: String?,
    val price: Value,
    @SerializedName("provider_name") val providerName: String,
    val company: String,
    val departure: AirportData,
    val arrival: AirportData,
    @SerializedName("has_transfer") val hasTransfer: Boolean
)
