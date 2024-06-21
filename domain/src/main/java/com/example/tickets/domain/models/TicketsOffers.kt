package com.example.tickets.domain.models

import com.google.gson.annotations.SerializedName

data class TicketsOffers(
    @SerializedName("tickets_offers") val ticketsOffers: List<TicketOffer>
)
