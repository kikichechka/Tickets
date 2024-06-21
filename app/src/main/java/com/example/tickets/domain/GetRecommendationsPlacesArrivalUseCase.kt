package com.example.tickets.domain

import com.example.tickets.data.TicketsRepositoryImpl
import com.example.tickets.domain.models.Converter
import com.example.tickets.domain.models.TicketOfferWithPicture
import javax.inject.Inject

class GetRecommendationsPlacesArrivalUseCase @Inject constructor(
    private val ticketsRepositoryImpl: TicketsRepositoryImpl
) {

    suspend fun getData(): List<TicketOfferWithPicture>? {
        val response = ticketsRepositoryImpl.getRecommendTickets()
        if (response.code() in 200..299) {
            return response.body()
                ?.let { Converter.converterTicketOfferToTicketOfferWithPicture(it) }
        }
        return null
    }
}
