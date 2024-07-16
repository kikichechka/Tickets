package com.example.tickets.domain.models

data class TicketDescription(
    val id: Int,
    val badge: String?,
    val price: Value,
    val providerName: String,
    val company: String,
    val departure: AirportData,
    val arrival: AirportData,
    val hasTransfer: Boolean
)
