package com.example.tickets.data.dto

import com.example.tickets.domain.models.AirportData

class AirportDataDTO(
    val town: String,
    val date: String,
    val airport: String
)

fun AirportDataDTO.toModel(): AirportData {
    return AirportData(
        town = town,
        date = date,
        airport = airport
    )
}