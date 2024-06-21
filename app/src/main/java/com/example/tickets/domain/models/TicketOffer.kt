package com.example.tickets.domain.models

import com.google.gson.annotations.SerializedName

data class TicketOffer(
    override val id: Int,
    override val title: String,
    @SerializedName("time_range") val timeRange: List<String>,
    override val price: Value
) : Ticket
