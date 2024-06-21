package com.example.tickets.domain.models

data class TicketOfferWithPicture(
    val picture: Int,
    override val id: Int,
    override val title: String,
    val timeRange: List<String>,
    override val price: Value
) : Ticket
