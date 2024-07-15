package com.example.tickets.domain

import com.example.tickets.domain.models.TicketsOffers
import com.example.tickets.domain.repository.TicketsRepository
import javax.inject.Inject

class GetRecommendationsPlacesArrivalUseCase @Inject constructor(
    private val ticketsRepository: TicketsRepository
) {

    suspend fun getData(): TicketsOffers? {
        val response = ticketsRepository.getRecommendTickets()
        if (response.code() in 200..299) {
            return response.body()
        }
        return null
    }
}
