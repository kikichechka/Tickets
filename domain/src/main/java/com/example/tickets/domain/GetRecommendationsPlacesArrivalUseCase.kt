package com.example.tickets.domain

import com.example.tickets.domain.models.Converter
import com.example.tickets.domain.models.TicketOfferWithPicture
import com.example.tickets.domain.repository.TicketsRepository
import javax.inject.Inject

class GetRecommendationsPlacesArrivalUseCase @Inject constructor(
    private val ticketsRepository: TicketsRepository
) {

    suspend fun getData(): List<TicketOfferWithPicture>? {
        val response = ticketsRepository.getRecommendTickets()
        if (response.code() in 200..299) {
            return response.body()
                ?.let { Converter.converterTicketOfferToTicketOfferWithPicture(it) }
        }
        return null
    }
}
