package com.example.tickets.domain

import com.example.tickets.domain.models.Offers
import com.example.tickets.domain.repository.TicketsRepository
import javax.inject.Inject

class GetOffersToFlyUseCase @Inject constructor(
    private val ticketsRepository: TicketsRepository
) {

    suspend fun getData(): Offers? {
        return ticketsRepository.getOffers()
    }
}
