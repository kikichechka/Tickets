package com.example.tickets.data.dto

import com.example.tickets.domain.models.Tickets

class TicketsDTO(
    val tickets: List<TicketDescriptionDTO>
)

fun TicketsDTO.toModel(): Tickets {
    return Tickets(tickets = tickets.map { it.toModel() })
}
