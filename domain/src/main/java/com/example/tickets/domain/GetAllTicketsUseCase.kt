package com.example.tickets.domain

import com.example.tickets.domain.models.TicketDescription
import com.example.tickets.domain.repository.TicketsRepository
import javax.inject.Inject

class GetAllTicketsUseCase @Inject constructor(
    private val ticketsRepository: TicketsRepository
) {

    suspend fun getData(): List<TicketDescription>? {
        return ticketsRepository.getAllTickets()?.tickets
    }
}
