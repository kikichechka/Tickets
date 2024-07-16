package com.example.tickets.data.dto

import com.example.tickets.domain.models.TicketDescription
import com.google.gson.annotations.SerializedName

class TicketDescriptionDTO(
    val id: Int,
    val badge: String?,
    val price: ValueDTO,
    @SerializedName("provider_name") val providerName: String,
    val company: String,
    val departure: AirportDataDTO,
    val arrival: AirportDataDTO,
    @SerializedName("has_transfer") val hasTransfer: Boolean
)

fun TicketDescriptionDTO.toModel(): TicketDescription {
    return TicketDescription(
        id = id,
        badge = badge,
        price = price.toModel(),
        providerName = providerName,
        company = company,
        departure = departure.toModel(),
        arrival = arrival.toModel(),
        hasTransfer = hasTransfer
    )
}
