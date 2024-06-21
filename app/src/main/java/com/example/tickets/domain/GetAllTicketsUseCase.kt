package com.example.tickets.domain

import com.example.tickets.data.TicketsRepositoryImpl
import com.example.tickets.domain.models.TicketDescription
import javax.inject.Inject

class GetAllTicketsUseCase @Inject constructor(
    private val ticketsRepositoryImpl: TicketsRepositoryImpl
) {

    suspend fun getData(): List<TicketDescription>? {
        val response = ticketsRepositoryImpl.getAllTickets()
        if (response.code() in 200..299) {
            return response.body()?.tickets
        }
        return null
    }
}
