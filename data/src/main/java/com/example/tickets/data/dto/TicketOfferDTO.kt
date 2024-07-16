package com.example.tickets.data.dto

import com.example.tickets.domain.models.TicketOffer
import com.google.gson.annotations.SerializedName

class TicketOfferDTO(
    val id: Int,
    val title: String,
    @SerializedName("time_range") val timeRange: List<String>,
    val price: ValueDTO
)

fun TicketOfferDTO.toModel(): TicketOffer {
    return TicketOffer(
        id = id,
        title = title,
        timeRange = timeRange,
        price = price.toModel()
    )
}
