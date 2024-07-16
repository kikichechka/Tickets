package com.example.tickets.data.dto

import com.example.tickets.domain.models.TicketsOffers
import com.google.gson.annotations.SerializedName

data class TicketsOffersDTO(
    @SerializedName("tickets_offers") val ticketsOffers: List<TicketOfferDTO>
)

fun TicketsOffersDTO.toModel(): TicketsOffers {
    return TicketsOffers(
        ticketsOffers = ticketsOffers.map { it.toModel() }
    )
}