package com.example.tickets.domain.models

data class TicketOffer(
    override val id: Int,
    override val title: String,
    val timeRange: List<String>,
    override val price: Value
) : Ticket
