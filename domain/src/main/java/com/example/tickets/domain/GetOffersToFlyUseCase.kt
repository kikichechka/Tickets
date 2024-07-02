package com.example.tickets.domain

import com.example.tickets.domain.models.Converter
import com.example.tickets.domain.models.OfferWithPicture
import com.example.tickets.domain.repository.TicketsRepository
import javax.inject.Inject

class GetOffersToFlyUseCase @Inject constructor(
    private val ticketsRepository: TicketsRepository
) {

    suspend fun getData(): List<OfferWithPicture>? {
        val response = ticketsRepository.getOffers()

        if (response.code() in 200..299) {
            return response.body()
                ?.let { Converter.converterOfferToOfferWithPicture(it) }
        }
        return null
    }
}
